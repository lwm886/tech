package com.tech.rm.stream.basic;

import org.apache.rocketmq.common.message.MessageConst;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @author lw
 * @since 2024/4/12
 */
@Component
public class ScProducer {
    @Resource
    private Source source;
    
    public void sendMessage(String msg){
        HashMap<String, Object> headers = new HashMap<>();
        headers.put(MessageConst.PROPERTY_TAGS,"tagA");
        MessageHeaders messageHeaders = new MessageHeaders(headers);
        Message<String> message = MessageBuilder.createMessage(msg, messageHeaders);
        this.source.output().send(message);
    }
    
}
