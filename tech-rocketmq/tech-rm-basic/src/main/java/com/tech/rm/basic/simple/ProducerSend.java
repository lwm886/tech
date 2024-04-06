package com.tech.rm.basic.simple;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;

/**
 * 简单消息发送：单向发送 & 同步发送
 * @author lw
 * @since 2024/4/6
 */
public class ProducerSend {
    public static void main(String[] args) throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer("ProducerGroupName");
        producer.setNamesrvAddr("192.168.50.152:9876;192.168.50.153:9876;192.168.50.154:9876");
        producer.start();
        for (int i = 0; i < 20; i++) {
            Message message = new Message("TopicTest", "TagA", "orderId188", "HW".getBytes(RemotingHelper.DEFAULT_CHARSET));
            //单向发送
//            producer.sendOneway(message);
            //同步发送
            SendResult sendResult = producer.send(message);
            System.out.printf("%s%n",sendResult);
        }
        producer.shutdown();
        System.out.println("send ok");
    }
}
