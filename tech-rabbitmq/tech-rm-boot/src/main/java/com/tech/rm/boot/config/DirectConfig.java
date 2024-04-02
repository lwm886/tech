package com.tech.rm.boot.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lw
 * @since 2024/4/2
 */
@Configuration
public class DirectConfig {
    @Bean
    public Queue directQueue1(){
        return new Queue("direct.queue1");
    }
    @Bean
    public Queue directQueue2(){
        return new Queue("direct.queue2");
    }
    @Bean
    public DirectExchange setDirectExchange(){
        return new DirectExchange("directExchange");
    }

    @Bean
    public Binding bindDirectQ1(){
        return BindingBuilder.bind(directQueue1()).to(setDirectExchange()).with("channa.bj");
    }

    @Bean
    public Binding bindDirectQ2(){
        return BindingBuilder.bind(directQueue2()).to(setDirectExchange()).with("channa.sh");
    }
}
