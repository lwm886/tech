package com.tech.rm.basic.simple;

import org.apache.rocketmq.client.consumer.DefaultLitePullConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * 轻量级Pull模式消费消息
 * @author lw
 * @since 2024/4/7
 */
public class LightPullConsumerSubscribe {
    public static volatile boolean running = true;

    public static void main(String[] args) throws MQClientException {
        DefaultLitePullConsumer consumer = new DefaultLitePullConsumer("lite_pull_g1");
        consumer.setNamesrvAddr("192.168.50.152:9876;192.168.50.153:9876;192.168.50.154:9876");
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.subscribe("TopicTestAsync","*");
        consumer.start();
        try{
            while (running){
                List<MessageExt> messageExts = consumer.poll();
                System.out.printf("%s%n",messageExts);
            }
        }finally {
            consumer.shutdown();
        }
    }
}
