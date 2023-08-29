package com.tech.ot.view;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.util.Deque;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.locks.LockSupport;

/**
 * 客户端点击播放：
 * 客户端与Netty服务端建立WebSocket连接，客户端发送请求播放的消息，服务端收到消息后，解析消息
 * 如果是请求播放，那么将这个通道添加到播放队列中，并检查数据推送任务是否启动，如果没有启动启动数据推送任务。
 * 
 * 数据推送任务订阅MQTT,将接收到的感知数据，推送给播放队列中的各个通道，当发现播放队列为空的时候，那么取消订阅MQTT,不再进行数据推送。
 * 
 * 停止播放：
 * 客户端发送停止播放的消息，服务端收到后解析消息，如果是请求停止播放，将该通道在数据播放队列移除。
 * 
 * @author lw
 * @since 2023/7/28
 */
public class CoordinationSocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    
    //需要播放的channel列表，也就是观察者们 (单例，不应该放到这里。。。)
    private final Set<Channel> displays=new ConcurrentSkipListSet();
    
    
    
    private volatile boolean run=false;
    
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("与客户端建"+ctx.channel().id()+"立连接，通道开启");
        //添加到channelGroup通道组
        CoordinationChannelHandlerPool.channelGroup.add(ctx.channel());
        CoordinationChannelHandlerPool.closes.add(ctx.channel());
        
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("与客户端断开连接，通道关闭");
//        从channelGroup通道组删除
        displays.remove(ctx.channel());
        CoordinationChannelHandlerPool.channelGroup.remove(ctx.channel());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) throws Exception {
        System.out.println("closes = "+CoordinationChannelHandlerPool.closes.size());

        //接收的消息
        System.out.println(String.format("收到客户端%s的数据：%s",channelHandlerContext.channel().id(),textWebSocketFrame.text()));
        if(textWebSocketFrame.text().equals("start")){
            displays.add(channelHandlerContext.channel());
            sendMessage(channelHandlerContext);
        }
        if(textWebSocketFrame.text().equals("stop")){
            displays.remove(channelHandlerContext.channel());
            //关闭通道
            channelHandlerContext.channel().close();
            
            //测试批量关闭缓存的通道
//            CoordinationChannelHandlerPool.closes.forEach(c->{
//                c.close();
//            });
        }
        //单发消息
//        sendMessage(channelHandlerContext);
        
        //群发消息
//        sendAllMessage();
    }
    
    
    
    private void sendMessage(ChannelHandlerContext ctx) throws InterruptedException{
      
//        String msg="我是服务器，你好啊";
//        ctx.writeAndFlush(new TextWebSocketFrame(msg));
        if(!run){
            run=true;
            new Thread(()->{
                Deque<String> queue = DataCache.queue;
                long lastPushTime=System.currentTimeMillis();
               
                while (true){
                    if(lastPushTime+1000>System.currentTimeMillis()){
                        LockSupport.parkUntil(lastPushTime+1000);
                    }
                    if(displays.isEmpty()){
                        continue;
                    }
                   
                    String msg = queue.pollLast();
                    if(msg==null){
                        continue;
                    }
                    for(Channel c:displays){
                        c.writeAndFlush(new TextWebSocketFrame(msg));
                    }
                    lastPushTime=System.currentTimeMillis();
                }
            }).start();
        }else{
            System.out.println("推送任务已被启动");
        }
        
       
    }
    private void sendAllMessage() throws InterruptedException{
        String msg="我是服务器，你好啊，这是群发消息";
        CoordinationChannelHandlerPool.channelGroup.writeAndFlush(new TextWebSocketFrame(msg));
    }
}
