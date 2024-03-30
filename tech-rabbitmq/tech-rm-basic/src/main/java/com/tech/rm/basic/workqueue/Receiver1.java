package com.tech.rm.basic.workqueue;

import com.rabbitmq.client.*;
import com.tech.rm.basic.utils.RmConstants;
import com.tech.rm.basic.utils.RmUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author lw
 * @since 2024/3/27
 */
public class Receiver1 {
    public static void main(String[] args) throws IOException {
        Connection connection = RmUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(RmConstants.QUEUE_SMS,false,false,false,null);
        
        //如果不写，则MQ将说有消息平均分发给所有消费者
        //设置basicQos后，某个消费者确认完对应的消息数量后再将新消息发送给这个消费者
        channel.basicQos(1);
        
        channel.basicConsume(RmConstants.QUEUE_SMS,false,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
     
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                String msg = new String(body);
                System.out.println("Receiver1 收到消息："+msg);
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        });
    }
}
