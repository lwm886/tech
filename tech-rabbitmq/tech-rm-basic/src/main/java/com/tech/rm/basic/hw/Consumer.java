package com.tech.rm.basic.hw;

import com.rabbitmq.client.*;
import com.tech.rm.basic.utils.RmConstants;
import com.tech.rm.basic.utils.RmUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @author lw
 * @since 2024/3/27
 */
@Slf4j
public class Consumer {
    public static void main(String[] args) throws IOException {
        //获取TCP长连接
        Connection connection = RmUtils.getConnection();
        //创建通道，相当于TCP中的虚拟连接
        Channel channel = connection.createChannel();
        //创建队列 args:【队列名称，是否持久化-false不持久化-MQ停掉数据就会丢失，是否队列私有化-false代表所有消费者都能访问-true代表只有第一次拥有它的消费者才能一直使用其它消费者不让访问，是否自动删除-false代表连接停掉后不自动删除这个队列，其它额外参数】
        channel.queueDeclare(RmConstants.QUEUE_HW,false,false,false,null);
        //消费消息 args: 【队列名称，是否自动确认收到消息-false表示手动去确认收到消息，DefaultConsumer的实现-包含了接收的消息以及手动确认的操作】
        channel.basicConsume(RmConstants.QUEUE_HW,false,new Receiver(channel));
    }
    
}

@Slf4j
class Receiver extends DefaultConsumer{
    private Channel channel;

    public Receiver(Channel channel) {
        super(channel);
        this.channel = channel;
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        String msg=new String(body);
        log.info("消费者接收到消息："+msg);
        System.out.println("消息的TagId："+envelope.getDeliveryTag());
        //false只确认签收当前消息，设置为true表示签收该消费者所有未签收的消息
        channel.basicAck(envelope.getDeliveryTag(), false);
    }
}

