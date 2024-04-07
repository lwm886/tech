package com.tech.rm.basic.simple;

import org.apache.rocketmq.client.consumer.DefaultLitePullConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 轻量级级PULL模式消费 重置消费者在队列中指定的位置消费
 * @author lw
 * @since 2024/4/7
 */
public class LightPullConsumerAssign {

    public static volatile boolean running = true;


    public static void main(String[] args) throws MQClientException {
        DefaultLitePullConsumer consumer = new DefaultLitePullConsumer("Lite-Pull-Consumer-G11");
        consumer.setNamesrvAddr("192.168.50.152:9876;192.168.50.153:9876;192.168.50.154:9876");
        consumer.setAutoCommit(false);
        consumer.start();
        //获取当前Topic的全部队列，默认一个Broker为一个Topic创建4个队列，2m-2s-async集群，总共有2个broker，8个队列 broker-a[0,1,2,3] broker-b[0,1,2,3]
        Collection<MessageQueue> queues = consumer.fetchMessageQueues("TopicTestAsync");
        List<MessageQueue> messageQueues = new ArrayList<>(queues);
        List<MessageQueue> assignList = new ArrayList<>();
        
        //测试前4个队列
        for (int i = 0; i < messageQueues.size() / 2; i++) {
            assignList.add(messageQueues.get(i));
        }
        //测试设置消费的队列
        consumer.assign(assignList);
        //测试设置第一个队列的消费位置
        consumer.seek(assignList.get(0),10);
        try{
            while (running){
                List<MessageExt> msgExts = consumer.poll();
                System.out.printf("%s %n",msgExts);
                consumer.commitSync();
            }
        }finally {
            consumer.shutdown();
        }
    }
}
