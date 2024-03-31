package com.tech.rm.basic;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author lw
 * @since 2024/3/31
 */
//@Component
public class QosListener implements ChannelAwareMessageListener {
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        //获取到的消息
        System.out.println(new String(message.getBody()));
        Thread.sleep(1000);
        //处理业务逻辑
        //进行消息的签收(配置文件指定了手动签收，如果不签收，消费者宕机unAck状态消息重新回到队列变为Ready)
//        channel.basicAck(message.getMessageProperties().getDeliveryTag(),true);
    }
}
