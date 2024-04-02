package com.tech.rm.boot.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author lw
 * @since 2024/4/2
 */
@Component
public class MqListener {
    
    @RabbitListener(queues="queue_hw")
    public void hw(String msg){
        System.out.println("直连模式收到消息："+msg);
    }
    
    
    @RabbitListener(queues="queue_work_queue")
    public void wq(String msg){
        System.out.println("1工作队列模式收到消息："+msg);
    }
    @RabbitListener(queues="queue_work_queue")
    public void wq1(String msg){
        System.out.println("2工作队列模式收到消息："+msg);
    }

    @RabbitListener(queues="fanout.queue1")
    public void ps1(String msg){
        System.out.println("1发布订阅模式收到消息："+msg);
    }
    @RabbitListener(queues="fanout.queue2")
    public void ps2(String msg){
        System.out.println("2发布订阅模式收到消息："+msg);
    }
    
    @RabbitListener(queues="direct.queue1")
    public void di1(String msg){
        System.out.println("1路由模式收到消息："+msg);
    }
    @RabbitListener(queues="direct.queue2")
    public void di2(String msg){
        System.out.println("2路由模式收到消息："+msg);
    }
    
    @RabbitListener(queues="topic.queue1")
    public void to1(String msg){
        System.out.println("1TOPIC模式收到消息："+msg);
    }
    @RabbitListener(queues="topic.queue2")
    public void to2(String msg){
        System.out.println("2TOPIC模式收到消息："+msg);
    }
}
