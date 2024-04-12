package com.tech.rm.stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;

@EnableBinding({Source.class, Sink.class})
@SpringBootApplication
public class TechRmStreamApplication {

    public static void main(String[] args) {
        SpringApplication.run(TechRmStreamApplication.class, args);
    }

}
