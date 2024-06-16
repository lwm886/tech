package com.tech.kafka.producer;

import com.alibaba.fastjson.JSON;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

/**
 * 消息发送端
 *
 * @author lw
 * @since 2024/6/16
 */
public class MsgProducer {
    private static final String TOPIC_NAME = "topic-a";

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.50.160:9092,192.168.50.160:9093,192.168.50.160:9096");
         /*
          发送消息ACK机制
         (1) ack=0 producer不需要等待任何broker确认收到消息的回复，就可以发送下一条消息，性能最高，但是最容易丢消息
         (2) ack=1 至少需要等待Leader已经成功将数据写入Log,但是不需要等待任何Follower同步写入，就可以发送下一条消息。如果Follower没有成功备份数据，Leader又返回了Ack挂掉，则消息丢失。
         (3) ack=-1或者all 需要等待在配置中配置的min.insync.replicas(默认为1，推荐大于等于2)个Follower同步备份数据成功，返回ACK。这种策略会保证只要有一个备份存活就不会丢失数据。这是最强的数据保证，一般除非是金融级别，或者跟钱打交道的场景才会使用这种配置。
         */
        props.put(ProducerConfig.ACKS_CONFIG, "1");
        /*
        发送失败会重试，默认重试间隔100ms,重试能保证消息发送的可靠性，但是也可能造成消息重复发送，比如网络抖动，所以需要消息接收者做好幂等性处理。
        */
        props.put(ProducerConfig.RETRIES_CONFIG, 3);
        /*
        消息发送重试间隔
         */
        props.put(ProducerConfig.RETRY_BACKOFF_MS_CONFIG, 300);
        /*
        设置消息发送的本地缓冲区，如果设置了该缓冲区，消息会先发送到本地缓冲区，可以提高消息发送性能，默认值是32MB
         */
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
        /*
        kafka本地线程会从缓冲区取数据，批量发送到broker,设置批量发送消息的大小默认是16K，一个Batch满了16K就发送出去
         */
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
        /*
        默认值是0，也就是消息必须被立即发送，但是这样会影响性能
        一般设置10ms左右，消息发送完后会进入本地的一个Batch,到10ms，这个Batch不管有没有满都会将这个Batch发送出去
         */
        props.put(ProducerConfig.LINGER_MS_CONFIG, 10);
        /*
        发送的key从字符串序列化为字节数组
         */
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        /*
        发送的value从字符串序列化为字节数组
         */
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        int msgNum = 5;
        final CountDownLatch countDownLatch = new CountDownLatch(msgNum);
        for (int i = 1; i <= msgNum; i++) {
            Order order = new Order(i, 100 + i, 1, 1000.00);
            //指定分区
//            ProducerRecord<String, String> producerRecord = new ProducerRecord<>(TOPIC_NAME, 0, order.getOrderId().toString(), JSON.toJSONString(order));
            //未指定分区，分区选择策略 hash(消息KEY)%分区数量
            ProducerRecord<String, String> producerRecord = new ProducerRecord<>(TOPIC_NAME,  order.getOrderId().toString(), JSON.toJSONString(order));
            //同步阻塞等待消息发送成功
            RecordMetadata metadata = producer.send(producerRecord).get();
            System.out.println("同步发送消息结果："+"topic-"+metadata.topic()+"|partition-"+metadata.partition()+"|offset"+metadata.offset());
            countDownLatch.countDown();
            
            //异步发送消息
          /*  producer.send(producerRecord, new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception e) {
                    if(e !=null){
                        System.err.println("发送消息失败："+e.getStackTrace());
                    }
                    if(metadata!=null){
                        System.out.println("异步发送消息结果："+"topic-"+metadata.topic()+"|partition-"+metadata.partition()+"|offset"+metadata.offset());
                    }
                    countDownLatch.countDown();
                }
            });*/
            
            //送积分 TODO
        }
        countDownLatch.await();
        producer.close();
    }
}
