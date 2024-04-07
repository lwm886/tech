package com.tech.rm.basic.simple;

import org.apache.rocketmq.client.consumer.DefaultMQPullConsumer;
import org.apache.rocketmq.client.consumer.PullResult;
import org.apache.rocketmq.client.consumer.PullStatus;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 早期deprecated  API PULL消息，消費者管理偏移量
 * @author lw
 * @since 2024/4/7
 */
public class PullConsumer {
    //消费者用于存储队列与其下一条消息消息的偏移量的对应关系
    private static final Map<MessageQueue,Long> OFFSET_TABLE=new HashMap<>();

    public static void main(String[] args) throws MQClientException {
        DefaultMQPullConsumer consumer = new DefaultMQPullConsumer("pull_group_1");
        consumer.setNamesrvAddr("192.168.50.152:9876;192.168.50.153:9876;192.168.50.154:9876");
        consumer.start();

        Set<MessageQueue> messageQueueSet = consumer.fetchSubscribeMessageQueues("TopicTestAsync");
        for (MessageQueue messageQueue:messageQueueSet){
            System.out.printf("Consume from the queue: %s%n",messageQueue);
            SINGLE_MQ:
            while (true){
                try {
                    PullResult pullResult = consumer.pullBlockIfNotFound(messageQueue, null, getMessageQueueOffset(messageQueue), 32);
                    System.out.printf("%s%n",pullResult);
                    putMessageQueueOffset(messageQueue,pullResult.getNextBeginOffset());
                    switch (pullResult.getPullStatus()){
                        case FOUND:
                            break ;
                        case NO_MATCHED_MSG:
                            break ;
                        case NO_NEW_MSG:
                            break SINGLE_MQ;
                        case OFFSET_ILLEGAL:
                            break ; 
                        default:
                            break ;
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                } 
            }
            
        }
        consumer.shutdown();

    }

    private static void putMessageQueueOffset(MessageQueue messageQueue, long nextBeginOffset) {
        OFFSET_TABLE.put(messageQueue,nextBeginOffset);
    }

    private static long getMessageQueueOffset(MessageQueue messageQueue){
        Long offset = OFFSET_TABLE.get(messageQueue);
        if(offset!=null){
            return offset;
        }
        return 0;
    }
}
