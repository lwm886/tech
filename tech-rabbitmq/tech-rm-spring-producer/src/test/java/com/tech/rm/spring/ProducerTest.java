package com.tech.rm.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @author lw
 * @since 2024/3/30
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-rabbitmq-producer.xml")
public class ProducerTest {
    
    @Resource
    RabbitTemplate rabbitTemplate;

    /**
     *  向默认交换机(direct类型)发送消息，路由KEY spring_queue
     */
    @Test
    public void testDefault(){
        rabbitTemplate.convertAndSend("spring_queue","hw spring");
    }
    

    /**
     *  向fanout交换机发送消息
     */
    @Test
    public void testFanout(){
        rabbitTemplate.convertAndSend("spring_fanout_exchange","","hw spring");
    }
    
    /**
     *  向direct交换机发送消息
     */
    @Test
    public void testDirect(){
        rabbitTemplate.convertAndSend("spring_direct_exchange","info","hw spring");
    }
    
    /**
     *  向topic交换机发送消息
     */
    @Test
    public void testTopic(){
        rabbitTemplate.convertAndSend("spring_topic_exchange","star","hw spring");
    }
    
    
}
