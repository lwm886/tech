package com.tech.rm.boot;

import com.google.common.collect.Maps;
import jdk.internal.org.objectweb.asm.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.MimeTypeUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author lw
 * @since 2024/4/12
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class SendTest {
    @Resource
    RocketMQTemplate rocketMQTemplate;
    
    @Test
    public void sendTest() throws InterruptedException {
        String topic="batch1";

        Map<String,String> user=new HashMap<>();
        user.put("name","zhangsan");
        user.put("age","100");
        
        //同步发送
       /* SendResult sendResult = rocketMQTemplate.syncSend(topic, "HW");
        log.info("syncSend res --------> {}", sendResult);*/

/*        SendResult sendResult1 = rocketMQTemplate.syncSend(topic, Lists.newArrayList("a"));
        log.info("sendResult1 res --------> {}", sendResult1);*/
        
/*        Map<String,String> user=new HashMap<>();
        user.put("name","zhangsan");
        user.put("age","100");
        SendResult sendResult = rocketMQTemplate.syncSend(topic, MessageBuilder
                .withPayload(user)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON_VALUE).build());
        log.info("sendResult res ------> {}",sendResult);*/
        

        //异步发送
     /*   rocketMQTemplate.asyncSend(topic, user, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("发送成功回调 sendResult：{}",sendResult);
            }
            @Override
            public void onException(Throwable throwable) {
                log.error("发送失败回调",throwable);
            }
        });
        System.out.println("发送完成");
        TimeUnit.SECONDS.sleep(100);*/
        
        //发送指定topic的消息
/*        rocketMQTemplate.convertAndSend(topic+":"+"tag0","selected msg");
        System.out.println("发送完成 TAG");
        rocketMQTemplate.convertAndSend(topic+":"+"tag1","selected msg");
        System.out.println("发送完成 TAG1");*/
        
        //发送延迟消息
/*        SendResult sendResult = rocketMQTemplate.syncSend(topic, MessageBuilder.withPayload("request string").build(), 15000, 1);
        log.info("delay Send result : {}",sendResult);*/
        
        //批量发送消息
        List< org.springframework.messaging.Message> msgs=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            org.springframework.messaging.Message<String> message = MessageBuilder.withPayload("Batch Msg:" + i).setHeader(RocketMQHeaders.KEYS, "key_" + i).build();
            msgs.add(message);
        }
        SendResult sendResult = rocketMQTemplate.syncSend(topic, msgs, 60000);
        log.info("Batch Send res ---------> {}",sendResult);
    }
}
