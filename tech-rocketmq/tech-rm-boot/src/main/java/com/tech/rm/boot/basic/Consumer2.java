package com.tech.rm.boot.basic;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @author lw
 * @since 2024/4/12
 */
@Slf4j
@Component
@RocketMQMessageListener(consumerGroup = "boot-b-g-batch",topic = "batch1",consumeMode = ConsumeMode.CONCURRENTLY)
public class Consumer2 implements RocketMQListener<MessageExt> {

    @Override
    public void onMessage(MessageExt messageExt) {
        System.out.println();
        System.out.println();
        log.info("RECEIVED MSG ======>> tags:{},id:{},body:{}",messageExt.getTags(),messageExt.getMsgId(),new String(messageExt.getBody()));
        System.out.println();
        System.out.println();
    }
}