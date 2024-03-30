package com.tech.rm.basic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

/**
 * @author lw
 * @since 2024/3/30
 */
@Slf4j
public class FanoutListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        System.out.println("receive msg: "+ new String(message.getBody()));
    }
}
