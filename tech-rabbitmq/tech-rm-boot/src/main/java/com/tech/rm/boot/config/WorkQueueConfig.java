package com.tech.rm.boot.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 简单模式队列
 * @author lw
 * @since 2024/4/2
 */
@Configuration
public class WorkQueueConfig {
    
    @Bean
    public Queue workQueue(){
        return new Queue("queue_work_queue");
    }
}
