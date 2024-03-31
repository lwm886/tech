package com.tech.rm.boot;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author lw
 * @since 2024/3/31
 */
@Component
public class RabbitMQListener {
    
    @RabbitListener(queues = "queue_boot")
    public void ListenerQueue(Message message){
        System.out.println("收到消息: "+new String(message.getBody()));
    }
}
