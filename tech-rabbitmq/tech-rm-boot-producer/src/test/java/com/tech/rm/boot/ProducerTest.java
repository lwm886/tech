package com.tech.rm.boot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author lw
 * @since 2024/3/31
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProducerTest {
    
    @Resource
    RabbitTemplate rabbitTemplate;
    
    @Test
    public void send(){
        rabbitTemplate.convertAndSend("exchange_boot","boot.haha","boot msg ...");
    }
}
