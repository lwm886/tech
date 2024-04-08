package com.tech.rm.basic.filter;

import lombok.extern.slf4j.Slf4j;
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
 * @since 2024/4/8
 */
@Slf4j
public class SqlFilterProducer {
    public static void main(String[] args) throws MQClientException, UnsupportedEncodingException, MQBrokerException, RemotingException, InterruptedException {
        DefaultMQProducer producer = new DefaultMQProducer("SQL-Filter-G1");
        producer.setNamesrvAddr("192.168.50.152:9876;192.168.50.153:9876;192.168.50.154:9876");
        producer.start();
        
        String[] tags = {"tagA", "tagB", "tagC"};
        for (int i = 0; i < 15; i++) {
            Message message = new Message("sqlTest", tags[i % tags.length], ("hw " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            message.putUserProperty("a",String.valueOf(i));
            SendResult sendResult = producer.send(message);
            log.info("send res: {}",sendResult);
        }
        
        producer.shutdown();
    }
}
