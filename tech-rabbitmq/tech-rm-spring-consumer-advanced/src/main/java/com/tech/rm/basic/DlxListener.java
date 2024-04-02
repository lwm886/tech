package com.tech.rm.basic;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author lw
 * @since 2024/3/31
 */
@Component
public class DlxListener implements ChannelAwareMessageListener {
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        //1.接收转换消息
        try {
            System.out.println(new String(message.getBody()));

            //2.处理业务逻辑
            System.out.println("处理业务逻辑");

            int i=1/0;
            System.out.println(i);

            //3.手动签收
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),true);
        } catch (Exception e) {
            System.out.println("出现异常，拒绝签收，且不重回队列");
            //拒绝签收，不重回队列
            channel.basicNack(message.getMessageProperties().getDeliveryTag(),true,false);
        }

    }
}
