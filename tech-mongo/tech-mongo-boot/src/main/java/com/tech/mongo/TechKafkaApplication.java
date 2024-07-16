package com.tech.mongo;

import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TechKafkaApplication {
    public static void main(String[] args) {
        SpringApplication.run(TechKafkaApplication.class, args);
    }
}
