package com.tech.rm.basic.trace;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;

/**
 * @author lw
 * @since 2024/4/11
 */
public class TraceProducer {
    public static void main(String[] args) throws MQClientException, UnsupportedEncodingException, MQBrokerException, RemotingException, InterruptedException {
        //开启消息轨迹
        DefaultMQProducer producer = new DefaultMQProducer("TRACE-G1", true);
        producer.setNamesrvAddr("192.168.50.157:9876;192.168.50.158:9876;192.168.50.159:9876");
        producer.start();
        for (int i = 0; i < 128; i++) {
            Message msg = new Message("TraceTopicTest", "tagA", "orderId111", "HW...".getBytes(RemotingHelper.DEFAULT_CHARSET));
            SendResult sendResult = producer.send(msg);
            System.out.println(sendResult);
        }
        
        producer.shutdown();
    }
}
