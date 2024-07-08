package com.tech.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.KafkaException;
import org.apache.kafka.common.errors.AuthorizationException;
import org.apache.kafka.common.errors.OutOfOrderSequenceException;
import org.apache.kafka.common.errors.ProducerFencedException;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * Kafka事务消息 
 *      保证向多个Topic、分区发送消息的操作作为一个原子性操作来执行，同时成功或同时失败，保证事务一致性
 * @author lw
 * @since 2024/7/8
 */
public class MsgTransactionProducer {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.50.161:9092,192.168.50.161:9093,192.168.50.161:9096");
        props.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG,"MY-TRANSACTIONAL-ID1");
    
        KafkaProducer<String, String> producer = new KafkaProducer<>(props, new StringSerializer(), new StringSerializer());
        //初始化事务
        producer.initTransactions();
        try {
            //开启事务
            producer.beginTransaction();
            for (int i = 0; i < 100; i++) {
                producer.send(new ProducerRecord<>("hdfs-topic",Integer.toString(i),Integer.toString(i)));
                producer.send(new ProducerRecord<>("es-topic",Integer.toString(i),Integer.toString(i)));
                producer.send(new ProducerRecord<>("redis-topic",Integer.toString(i),Integer.toString(i)));
            }
            //提交事务
            producer.commitTransaction();
            System.out.println("提交事务成功");
        }catch (ProducerFencedException | OutOfOrderSequenceException | AuthorizationException e){
            producer.close();
            System.out.println("操作异常，关闭生产者");
            return;
        }catch (KafkaException e){
            //回滚事务
            producer.abortTransaction();
            System.out.println("回滚事务");
        }
        producer.close();
        System.out.println("操作结束，关闭生产者");
    }
}
