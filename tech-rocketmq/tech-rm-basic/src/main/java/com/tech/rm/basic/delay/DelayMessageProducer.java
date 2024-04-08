package com.tech.rm.basic.delay;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * @author lw
 * @since 2024/4/8
 */
public class DelayMessageProducer {
    public static void main(String[] args) throws MQClientException, MQBrokerException, RemotingException, InterruptedException {
        DefaultMQProducer producer = new DefaultMQProducer("delay-g1");
        producer.setNamesrvAddr("192.168.50.152:9876;192.168.50.153:9876;192.168.50.154:9876");

        producer.start();
        for (int i = 0; i < 100; i++) {
            Message message = new Message("delayTopic", ("HW " + i).getBytes());
            //社区开源版不支持在api中直接设置时间，需要配置延迟等级
            //延迟等级总共18个，设置对应的延迟等级 messageDelayLevel	1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h，延迟等级对应的延迟时间在搭建集群时能够配置
            //延迟消息会先被投递到 SCHEDULE_TOPIC_XXXX临时TOPIC中，每个broker会为这个TOPIC准备18个队列,对应18个延迟等级，RocketMQ会周期性取出到达延迟时间的消息发送到真正要发送的队列中
            message.setDelayTimeLevel(5);
            producer.send(message);
        }
        producer.shutdown();
        System.out.println("Delayed Msg Send Ok");
    }
}
