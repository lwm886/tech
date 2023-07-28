package com.tech.ot.view;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * Netty Server
 *
 * @author lw
 * @since 2023/7/28
 */
public class NettyServer {

    private final int port;

    public NettyServer(int port) {
        this.port = port;
    }

    public void start() throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            //未完成握手队列长度和已完成握手队列长度和，超过这个值请求不会被处理
            serverBootstrap.option(ChannelOption.SO_BACKLOG, 1024);
            serverBootstrap.group(group, bossGroup) //绑定线程池
                    .channel(NioServerSocketChannel.class) //指定使用的channel
                    .localAddress(this.port) //绑定监听端口
                    .childHandler(new ChannelInitializer<SocketChannel>() { //绑定客户端连接时触发操作
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            //webSocket协议是基于http协议的，所以这里也要使用http编解码器
                            socketChannel.pipeline().addLast(new HttpServerCodec());
                            //以块的方式来写的处理器
                            socketChannel.pipeline().addLast(new ChunkedWriteHandler());
                            socketChannel.pipeline().addLast(new HttpObjectAggregator(8192));
                            socketChannel.pipeline().addLast(new WebSocketServerProtocolHandler("/ws", "WebSocket", true, 65536 * 10));
                            socketChannel.pipeline().addLast(new CoordinationSocketHandler()); //自定义消息处理类
                        }
                    });
            ChannelFuture channelFuture = serverBootstrap.bind().sync(); //服务器异步创建绑定
            System.out.println(NettyServer.class + "已启动，正在监听：" + channelFuture.channel().localAddress());
            channelFuture.channel().closeFuture().sync();

        } finally {
            group.shutdownGracefully().sync(); //释放线程池资源
            bossGroup.shutdownGracefully().sync();
        }
    }
}
