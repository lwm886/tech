package com.tech.ot.view;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * @author lw
 * @since 2023/7/28
 */
public class CoordinationChannelHandlerPool {
    public CoordinationChannelHandlerPool() {
    }
    
    public static ChannelGroup channelGroup=new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    //测试批量关闭通道
    public static final Set<Channel> closes=new ConcurrentSkipListSet();
}
