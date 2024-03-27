package com.tech.rm.simple.hw;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.tech.rm.simple.utils.RmConstants;
import com.tech.rm.simple.utils.RmUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author lw
 * @since 2024/3/27
 */
@Slf4j
public class Producer {
    public static void main(String[] args) throws IOException, TimeoutException {
        //获取TCP长连接
        Connection connection = RmUtils.getConnection();
        //创建通道，相当于TCP中的虚拟连接
        Channel channel = connection.createChannel();
        //创建队列 args:【队列名称，是否持久化-false不持久化-MQ停掉数据就会丢失，是否队列私有化-false代表所有消费者都能访问-true代表只有第一次拥有它的消费者才能一直使用其它消费者不让访问，是否自动删除-false代表连接停掉后不自动删除这个队列，其它额外参数】
        channel.queueDeclare(RmConstants.QUEUE_HW,false,false,false,null);
        String msg="hw "+ System.currentTimeMillis();
        //发布消息 args:【交换机，队列名称，额外属性，消息对应的字节数组】
        channel.basicPublish("",RmConstants.QUEUE_HW,null,msg.getBytes());
        channel.close();
        connection.close();
        log.info("====发送成功===");
    }
}
