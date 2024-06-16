package com.tech.kafka.producer;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

/**
 * 消息消费端
 *
 * @author lw
 * @since 2024/6/16
 */
public class MsgConsumer {
    private static final String TOPIC_NAME = "topic-a";
    private static final String CONSUMER_GROUP_NAME = "group-a";

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.50.160:9092,192.168.50.160:9093,192.168.50.160:9096");
        //消费分组名
        props.put(ConsumerConfig.GROUP_ID_CONFIG, CONSUMER_GROUP_NAME);
       /* //是否自动提交offset 默认是true
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,"true");
        //自动提交offset的时间间隔
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG,"1000");*/
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");

        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList(TOPIC_NAME));
        while (true) {
            /*
            poll 通过长轮询机制拉取消息，参数为长轮询的时间，当本次poll时间达到长轮询时间或者本次poll到的消息数达到配置的单次poll的最大消息数量，则poll返回。
             */
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
            for (ConsumerRecord<String, String> record : records) {
                System.out.println("收到消息：partition = " + record.partition() + ", offset = " + record.offset() + ", key = " + record.key() + ", value = " + record.value());

            }
            if (records.count() > 0) {
                //手动同步提交offset，当前线程会阻塞直到offset提交成功
                //一般使用同步提交，因为一般提交之后一般也没有什么逻辑代码了
                /*consumer.commitSync();
                System.out.println("手动同步提交offset成功");*/


                //手动异步提交offset，当前线程提交offset不会阻塞，可以继续处理后面的程序逻辑
                consumer.commitAsync(new OffsetCommitCallback() {
                    @Override
                    public void onComplete(Map<TopicPartition, OffsetAndMetadata> offsets, Exception exception) {
                        if (exception != null) {
                            System.err.println("Commit failed for: "+offsets);
                            System.err.println("Commit failed exception: "+exception.getStackTrace());
                        }else{
                            System.out.println("手动异步提交offset成功");
                        }
                    }
                });

            }

        }

    }
}
