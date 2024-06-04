package com.tech.rm.basic.transaction;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * RocketMQ事务消息，是分布式系统中最终一致性两阶段提交的一个实现，它能保证发送事务消息和执行本地事务作为一个原子性操作来执行，也就是同时成功和失败。
 * 生产者投递消息到Broker
 * 生产者执行本地事务，向Broker发送事务状态
 *      如果发送事务状态是COMMIT,则消费者能够消费这条事务消息
 *      如果发送事务状态是RollBack,则Broker将会丢弃这条事务消息，消费者不会消费到这条消息
 *      如果发送事务状态是UNKNOWN,则等待达到配置的检查时间，Broker会回查生产者的本地事务，此时生产者检查本地事务，向Broker返回本地事务状态：
 *          如果发送事务状态是COMMIT,则消费者能够消费这条事务消息
 *          如果发送事务状态是RollBack,则Broker将会丢弃这条事务消息，消费者不会消费到这条消息
 *          如果发送事务状态是UNKNOWN，达到配置的回查间隔时间broker会继续回查生产者，当达到配置的最大回查次数，得到的事务状态依旧是UNKNOWN状态，则Broker会丢弃这条事务消息
 * Rocker发送的事务消息，首先会被发送到一个临时Topic中，当事务消息状态被生产者确认后，再被Broker转发到目标Topic         
 * @author lw
 * @since 2024/4/11
 */
public class TransactionProducer {

    public static void main(String[] args) throws MQClientException, UnsupportedEncodingException, InterruptedException {
        TransactionMQProducer producer = new TransactionMQProducer("TRANSACTION-G1");
        producer.setNamesrvAddr("192.168.50.157:9876;192.168.50.158:9876;192.168.50.159:9876");
        ExecutorService exec=new ThreadPoolExecutor(2, 5, 100, TimeUnit.SECONDS, new ArrayBlockingQueue<>(2000), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setName("client-transaction-msg-check-thread");
                return thread;
            }
        });
        producer.setExecutorService(exec);
        TransactionListener transactionListener=new TransactionListenerImpl();
        producer.setTransactionListener(transactionListener);
        producer.start();

        String[] tags = {"tagA", "tagB", "tagC", "tagD", "tagE"};
        for (int i = 0; i < 10; i++) {
            Message message = new Message("TopicTestAsync", tags[i % tags.length], "KEY" + i, "HW TRANSACTION MSG".getBytes(RemotingHelper.DEFAULT_CHARSET));
            TransactionSendResult sendResult = producer.sendMessageInTransaction(message, null);
            System.out.printf("%s%n",sendResult);
            TimeUnit.MILLISECONDS.sleep(10);
        }

        TimeUnit.MINUTES.sleep(10);
        producer.shutdown();
    }
    
}

@Slf4j
class TransactionListenerImpl implements TransactionListener{
    
    private AtomicInteger transactionIndex=new AtomicInteger(0);
    
    private ConcurrentHashMap<String,Integer> localTrans=new ConcurrentHashMap<>();
    
    
    //生产者执行本地事务
    @Override
    public LocalTransactionState executeLocalTransaction(Message message, Object o) {
      /*  int val = transactionIndex.getAndIncrement();
        int status = val % 3;
        localTrans.put(message.getTransactionId(),status);
        log.info("executeLocalTransaction...... transactionId:{},status:{} return LocalTransactionState.UNKNOW",message.getTransactionId(),status);
        return LocalTransactionState.UNKNOW;*/
        String tags = message.getTags();
        if("tagA".equals(tags)){
            return LocalTransactionState.COMMIT_MESSAGE;
        }else if("tagB".equals(tags)){
            return LocalTransactionState.ROLLBACK_MESSAGE;
        }else{
            return LocalTransactionState.UNKNOW;
        }

    }
    
    //broker回查生产者本地事务,能够在Broker上配置发送事务消息后多久开始回查本地事务，当回查的状态为UNKNOWN时，会再次回查，直到达到配置的最大回查次数，在broker上能够配置最大回查次数，当达到最大回查次数时将会丢失这条UNKNOWN状态的消息
    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt msg) {
        /*Integer status = localTrans.get(msg.getTransactionId());
        switch (status){
            case 0:
                log.info("checkLocalTransaction...... transactionId:{},status:{} return LocalTransactionState.UNKNOW",msg.getTransactionId(),status);
                return LocalTransactionState.UNKNOW;
            case 1:
                log.info("checkLocalTransaction...... transactionId:{},status:{} return LocalTransactionState.COMMIT_MESSAGE",msg.getTransactionId(),status);
                return LocalTransactionState.COMMIT_MESSAGE;
            case 2:
                log.info("checkLocalTransaction...... transactionId:{},status:{} return LocalTransactionState.ROLLBACK_MESSAGE",msg.getTransactionId(),status);
                return LocalTransactionState.ROLLBACK_MESSAGE;
            default:
                log.info("checkLocalTransaction...... transactionId:{},status:{} return LocalTransactionState.COMMIT_MESSAGE",msg.getTransactionId(),status);
                return LocalTransactionState.COMMIT_MESSAGE;
        }*/
        String tags = msg.getTags();
        if("tagC".equals(tags)){
            return LocalTransactionState.COMMIT_MESSAGE;
        }else if("tagD".equals(tags)){
            return LocalTransactionState.ROLLBACK_MESSAGE;
        }else {
            return LocalTransactionState.UNKNOW;
        }

    }
}