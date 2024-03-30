package com.tech.rm.basic.confirm1;


import com.rabbitmq.client.*;
import com.tech.rm.basic.utils.RmConstants;
import com.tech.rm.basic.utils.RmUtils;

import java.io.IOException;

public class Sina {
    public static void main(String[] args) throws IOException {
        Connection connection = RmUtils.getConnection();
        final Channel channel = connection.createChannel();
        channel.queueDeclare(RmConstants.QUEUE_SINA, false, false, false, null);

        channel.queueBind(RmConstants.QUEUE_SINA, RmConstants.EXCHANGE_WEATHER_TOPIC, "us.#");

        channel.basicQos(1);
        channel.basicConsume(RmConstants.QUEUE_SINA , false , new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("腾讯天气收到气象信息：" + new String(body));
                channel.basicAck(envelope.getDeliveryTag() , false);
            }
        });
    }
}
