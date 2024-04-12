package com.tech.rm.stream.basic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * @author lw
 * @since 2024/4/12
 */
@Slf4j
@Component
public class ScConsumer {
    @StreamListener(Sink.INPUT)
    public void onMessage(Message<String> message){
        log.info("Receive Message: headers:{} payLoad:{}",message.getHeaders(),message.getPayload());
    }
}
