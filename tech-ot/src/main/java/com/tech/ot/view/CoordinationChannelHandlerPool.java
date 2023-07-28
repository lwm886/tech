package com.tech.ot.view;

import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @author lw
 * @since 2023/7/28
 */
public class CoordinationChannelHandlerPool {
    public CoordinationChannelHandlerPool() {
    }
    
    public static ChannelGroup channelGroup=new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
}
