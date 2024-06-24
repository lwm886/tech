package com.tech.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

/**
 * @author lw
 * @since 2024/6/24
 */
@Component
public class MyConsumer {
    
    /*
        @KafkaListener(groupId = "testGroup",topicPartitions = {
            @TopicPartition(topic = "topic1",partitions = {"0","1"}),
            @TopicPartition(topic = "topic2",partitions = "0",partitionOffsets = @PartitionOffset(partition = "1",initialOffset = "100"))
    },concurrency = "6")
        //concurrency就是同组下的消费者个数，就是并发消费数，必须小于等于分区总数

     */

    @KafkaListener(topics = "topic-tst-1",groupId = "g1")
    public void kafkaListener(ConsumerRecord<String,String> record, Acknowledgment ack){
        String value = record.value();
        System.out.println(Thread.currentThread().getName()+value);
        System.out.println(Thread.currentThread().getName()+record);
        //手动提交偏移量
        ack.acknowledge();
    }

    @KafkaListener(topics = "topic-b",groupId = "g2")
    public void kafkaListener1(ConsumerRecord<String,String> record, Acknowledgment ack){
        String value = record.value();
        System.out.println(value+"|");
        System.out.println(record);
        //手动提交偏移量
        ack.acknowledge();
    }
    
}
