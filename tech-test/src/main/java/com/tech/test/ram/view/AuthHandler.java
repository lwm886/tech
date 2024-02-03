package com.tech.test.ram.view;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * 入栈处理器拦截WS握手HTTP请求，在读取事件中，判断请求为HTTP请求，解析出URL中的token，验证有效性，有效重置请求的URI(截掉token)，无效关闭通道
 * @author lw
 * @since 2023/8/29
 */
public class AuthHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(msg instanceof FullHttpRequest){
            FullHttpRequest req=(FullHttpRequest)msg;
            String uri = req.uri();
            String token=getToken(uri);
            if(token.equals("666")){
                System.out.println(token+" token 验证成功");
                req.setUri("/ws");
            }else{
                System.out.println("token无效");
                ctx.close();
            }
            
        }
            super.channelRead(ctx, msg);
    }

    private String getToken(String uri) {
        return uri.split("token=")[1];
    }
}
