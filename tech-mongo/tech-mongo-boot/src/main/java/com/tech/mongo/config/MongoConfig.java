package com.tech.mongo.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * @author lw
 * @since 2024/7/16
 */
@Configuration
public class MongoConfig {
    
    @Bean
    public MongoClient mongoClient(){
        return MongoClients.create("mongodb://192.168.50.161:27017");
    }
    @Bean
    public MongoTemplate mongoTemplate(){
        return new MongoTemplate(mongoClient(),"productdb");
    }
}
