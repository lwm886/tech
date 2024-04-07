package com.tech.rm.basic.orderly;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * 发送顺序消息
 * @author lw
 * @since 2024/4/7
 */
public class Producer {
    public static void main(String[] args) throws MQClientException, UnsupportedEncodingException, MQBrokerException, RemotingException, InterruptedException {
        DefaultMQProducer producer = new DefaultMQProducer("orderly-group-1");
        producer.setNamesrvAddr("192.168.50.152:9876;192.168.50.153:9876;192.168.50.154:9876");
        producer.start();
        for (int i = 0; i < 10; i++) {
            int orderId=i;
            for (int j = 0; j <=5; j++) {
                Message msg = new Message("topic-orderly-test", "orderId:" + orderId, "key:" + orderId, ("order_" + orderId + " step " + j).getBytes(RemotingHelper.DEFAULT_CHARSET));
                SendResult sendResult = producer.send(msg, new MessageQueueSelector() {
                    //arg的值为后面的参数orderId
                    @Override
                    public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                        Integer id = (Integer) arg;
                        int index = id % mqs.size();
                        return mqs.get(index);
                    }
                }, orderId);
                System.out.printf("%s%n",sendResult);
            }
        }
        producer.shutdown();
    }
}
