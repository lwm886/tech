package com.tech.rm.boot.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 直连模式
 * @author lw
 * @since 2024/4/2
 */
@Configuration
public class HwConfig {
    
    @Bean
    public Queue setQueue(){
        return new Queue("queue_hw");
    }
}
