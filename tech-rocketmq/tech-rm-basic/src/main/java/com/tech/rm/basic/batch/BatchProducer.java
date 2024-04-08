package com.tech.rm.basic.batch;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.ArrayList;
import java.util.List;

/**
 * 批量消息：将多条消息作为一条个批量消息进行发送，降低网络开销，提升系统吞吐
 * 条件：批量消息必须订阅相同topic，且不能是延迟消息和事务消息
 * @author lw
 * @since 2024/4/8
 */
public class BatchProducer {
    public static void main(String[] args) throws MQClientException, MQBrokerException, RemotingException, InterruptedException {
        DefaultMQProducer producer = new DefaultMQProducer("batch-producer-g1");
        producer.setNamesrvAddr("192.168.50.152:9876;192.168.50.153:9876;192.168.50.154:9876");
        producer.start();
        
        String topic="BatchTest";
        List<Message> messages = new ArrayList<>();
        messages.add(new Message(topic,"Tag","orderId1","hw1".getBytes()));
        messages.add(new Message(topic,"Tag","orderId2","hw2".getBytes()));
        messages.add(new Message(topic,"Tag","orderId3","hw3".getBytes()));
        
        producer.send(messages);
        producer.shutdown();
    }
}
