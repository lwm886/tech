package com.tech.rm.boot.basic;

import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author lw
 * @since 2024/4/12
 */
@Component
public class Producer {

    @Resource
    RocketMQTemplate rocketMQTemplate;


    public void sendMessage(String topic,String msg){
        this.rocketMQTemplate.convertAndSend(topic,msg);
    }

    public void sendMessageInTransaction(String topic,String msg) throws InterruptedException {
        String[] tags = {"tagA", "tagB", "tagC", "tagD", "tagE"};
        for (int i = 0; i < 10; i++) {
            Message<String> message = MessageBuilder.withPayload(msg)
                    .setHeader(RocketMQHeaders.TAGS, tags[i % tags.length])
                    .setHeader("myProp", "MyProp:" + i)
                    .build();
            String destination=topic+":"+tags[i% tags.length];
            TransactionSendResult sendResult = rocketMQTemplate.sendMessageInTransaction(destination, message, destination);
            System.out.println(sendResult);
            TimeUnit.MILLISECONDS.sleep(10);
        }

    }



}
