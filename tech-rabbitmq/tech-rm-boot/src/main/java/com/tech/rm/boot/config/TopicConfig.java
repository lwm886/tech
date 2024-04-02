package com.tech.rm.boot.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lw
 * @since 2024/4/2
 */
@Configuration
public class TopicConfig {
    @Bean
    public Queue topicQueue1(){
        return new Queue("topic.queue1");
    }
    @Bean
    public Queue topicQueue2(){
        return new Queue("topic.queue2");
    }
    @Bean
    public TopicExchange setTopicExchange(){
        return new TopicExchange("topicExchange");
    }

    @Bean
    public Binding bindTopicQ1(){
        return BindingBuilder.bind(topicQueue1()).to(setTopicExchange()).with("channa.*");
    }

    @Bean
    public Binding bindTopicQ2(){
        return BindingBuilder.bind(topicQueue2()).to(setTopicExchange()).with("#.sh");
    }
}
