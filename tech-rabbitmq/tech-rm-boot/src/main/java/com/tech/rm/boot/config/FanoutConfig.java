package com.tech.rm.boot.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lw
 * @since 2024/4/2
 */
@Configuration
public class FanoutConfig {
    @Bean
    public Queue fanoutQueue1(){
        return new Queue("fanout.queue1");
    }
    @Bean
    public Queue fanoutQueue2(){
        return new Queue("fanout.queue2");
    }
    
    @Bean
    public FanoutExchange setFanoutExchange(){
        return new FanoutExchange("fanoutExchange");
    }
    
    @Bean
    public Binding bindQ1(){
        return BindingBuilder.bind(fanoutQueue1()).to(setFanoutExchange());
    }
    
    @Bean
    public Binding bindQ2(){
        return BindingBuilder.bind(fanoutQueue2()).to(setFanoutExchange());
    }
}
