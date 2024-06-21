package com.tech.kafka.producer;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.*;

/**
 * 消息消费端
 *
 * @author lw
 * @since 2024/6/16
 */
public class MsgConsumer {
    private static final String TOPIC_NAME = "topic-b";
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
      
        //当一个新的消费者组第一次消费时，或者指定offset进行消费但是指定的offset不存在，从哪个位置进行消费：
        //latest(默认) 只消费消费者启动后发送到Topic的消息
        //earliest 第一次从头开始消费，以后按照消费offset记录（接着已提交的offset）进行消费，这个需要区别于consumer.seekToBeginning(每次都从头开始消费)
//        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");
        
        //consumer给Broker发送心跳的时间间隔，如果此时有Rebalance，Broker会将Rebalance策略通过心跳响应发送给Consumer，这个时间应该稍短些
        props.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG,1000);
        
        //Broker在这个时间里如果没有感知到consumer发送的心跳，则任务它已经故障，会将它踢出消费者组，通过Rebalance机制将分区重新分配给其它消费者，默认是10s
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG,10*1000);
        
        //一次poll拉取的最大消息条目数，如果消费处理速度快，适当调大该值，如果消费处理速度一般，适当调低该值
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG,50);
        
        //两次Poll的最大时间间隔，如果两次Poll的时间间隔超过该值，Broker会认为消费者处理能力太弱，将其踢出消费者，通过Rebalance机制将分区重新分配给其它消费者。
        props.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG,30*1000);
        
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
//        consumer.subscribe(Arrays.asList(TOPIC_NAME));
        
        //消费指定分区
//        consumer.assign(Arrays.asList(new TopicPartition(TOPIC_NAME,0)));
        
        //消息回溯 每次启动都从分区的第一条消息消费   需要先指定消费分区例如 consumer.assign
//        consumer.assign(Arrays.asList(new TopicPartition(TOPIC_NAME,0)));
//        consumer.seekToBeginning(Arrays.asList(new TopicPartition(TOPIC_NAME,0)));
        
        //每次启动都从指定分区的指定offset开始消费（包含该offset）   需要先指定消费分区例如 consumer.assign
        consumer.assign(Arrays.asList(new TopicPartition(TOPIC_NAME,0)));
        consumer.seek(new TopicPartition(TOPIC_NAME,0),10);

        //从1小时前消费(每次启动都从这个位置消费) 首先获取Topic的各个分区在指定时间时的最新消息的offset---然后再设置消费者在对应分区上指定的offset开始消费
     /*   List<PartitionInfo> partitionInfos = consumer.partitionsFor(TOPIC_NAME);
        long fetchDateTime=new Date().getTime()-1000*60*60;
        HashMap<TopicPartition, Long> map = new HashMap<>();
        for (PartitionInfo partitionInfo:partitionInfos){
            map.put(new TopicPartition(TOPIC_NAME,partitionInfo.partition()),fetchDateTime);
        }
        Map<TopicPartition, OffsetAndTimestamp> parMap = consumer.offsetsForTimes(map);
        Set<Map.Entry<TopicPartition, OffsetAndTimestamp>> entries = parMap.entrySet();
        for (Map.Entry<TopicPartition, OffsetAndTimestamp> entry:entries){
            TopicPartition key = entry.getKey();
            OffsetAndTimestamp value = entry.getValue();
            if(key == null || value == null){
                continue;
            }
            long offset = value.offset();
            System.out.println("partition-"+key.partition() + "|offset-"+offset);
            System.out.println();
            
            if(value !=null){
                //从指定分区的指定offset往后消费 需要先指定分区例如consumer.assign
                consumer.assign(Arrays.asList(key));
                consumer.seek(key,offset);
            }
        }*/
        

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
                consumer.commitSync();
                System.out.println("手动同步提交offset成功");


                //手动异步提交offset，当前线程提交offset不会阻塞，可以继续处理后面的程序逻辑
                /*consumer.commitAsync(new OffsetCommitCallback() {
                    @Override
                    public void onComplete(Map<TopicPartition, OffsetAndMetadata> offsets, Exception exception) {
                        if (exception != null) {
                            System.err.println("Commit failed for: "+offsets);
                            System.err.println("Commit failed exception: "+exception.getStackTrace());
                        }else{
                            System.out.println("手动异步提交offset成功");
                        }
                    }
                });*/

            }

        }

    }
}
