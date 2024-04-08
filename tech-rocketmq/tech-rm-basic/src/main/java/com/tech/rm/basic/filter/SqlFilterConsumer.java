package com.tech.rm.basic.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MessageSelector;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * @author lw
 * @since 2024/4/8
 */
@Slf4j
public class SqlFilterConsumer {
    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("SQL-F-C-11");
        consumer.setNamesrvAddr("192.168.50.152:9876;192.168.50.153:9876;192.168.50.154:9876");
        //按照TAG和SQL过滤，SQL过滤只能适用于PUSH模式消费消息
        //SQL过滤 需要在broker配置中进行开启enablePropertyFilter=true
        consumer.subscribe("sqlTest", MessageSelector.bySql("(TAGS is not null and TAGS in ('tagA','tagB')) and (a is not null and a between 0 and 3)"));
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                log.info("Receive new msg: {}",list);
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();
        System.out.println("Consumer started.");
    }
}
