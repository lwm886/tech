package com.tech.rm.basic.routing;

import com.rabbitmq.client.*;
import com.tech.rm.basic.utils.RmConstants;
import com.tech.rm.basic.utils.RmUtils;

import java.io.IOException;

/**
 * @author lw
 * @since 2024/3/30
 */
public class Sina {
    public static void main(String[] args) throws IOException {
        Connection connection = RmUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(RmConstants.QUEUE_SINA, false,false,false,null);
        
        //绑定交换机与队列
        //args[队列名称，交换机名，路由Key]
        channel.queueBind(RmConstants.QUEUE_SINA,RmConstants.EXCHANGE_WEATHER_ROUTING,"us.cal.lsj.20201127");
        channel.queueBind(RmConstants.QUEUE_SINA,RmConstants.EXCHANGE_WEATHER_ROUTING,"china.hubei.wuhan.20201127");
        channel.queueBind(RmConstants.QUEUE_SINA,RmConstants.EXCHANGE_WEATHER_ROUTING,"us.cal.lsj.20201128");
        
        //消费者未确认的消息数量达到该值时，MQ将会暂停给该消费者发送消息
        channel.basicQos(1);
        
        channel.basicConsume(RmConstants.QUEUE_SINA,false,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("新浪天气收到气象信息："+new String(body));
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        });
    }
}
