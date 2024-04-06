package com.tech.rm.basic.simple;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 简单消息发送 异步发送
 * @author lw
 * @since 2024/4/6
 */
public class AsyncProducerSend {
    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, UnsupportedEncodingException {
        DefaultMQProducer producer = new DefaultMQProducer("ProducerGroupName");
        producer.setNamesrvAddr("192.168.50.152:9876;192.168.50.153:9876;192.168.50.154:9876");
        producer.start();
        producer.setRetryTimesWhenSendAsyncFailed(0);
        
        int messageCount=100;
        //保证发送消息的回调全部执行完，再停掉producer
        final CountDownLatch countDownLatch=new CountDownLatch(messageCount);
        for (int i = 0; i < messageCount; i++) {
            final int index=i;
            Message msg = new Message("TopicTestAsync", "TagB", "orderId111", "HI ASYNC MSG...".getBytes(RemotingHelper.DEFAULT_CHARSET));
            producer.send(msg, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    countDownLatch.countDown();
                    System.out.printf("%-10d OK %s %n",index,sendResult.getMsgId());
                }

                @Override
                public void onException(Throwable e) {
                    countDownLatch.countDown();
                    System.out.printf("%-10d Exception %s %n",index,e);
                    e.printStackTrace();
                }
            });
            System.out.println("消息发送完成 "+index);
        }
        countDownLatch.await(10, TimeUnit.SECONDS);
        producer.shutdown();
    }
    
}
