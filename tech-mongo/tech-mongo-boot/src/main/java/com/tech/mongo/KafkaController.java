package com.tech.mongo;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author lw
 * @since 2024/6/24
 */
@RequestMapping
@RestController
public class KafkaController {
    private static final String TOPIC_NAME="topic-tst-1";
    
    @Resource
    private KafkaTemplate<String,String> kafkaTemplate;
    
    @RequestMapping("send")
    public void send(){
        kafkaTemplate.send(TOPIC_NAME,0,"KEY","this is a msg");
        kafkaTemplate.send(TOPIC_NAME,1,"KEY","this is a msga");
        kafkaTemplate.send(TOPIC_NAME,2,"KEY","this is a msgb");
    }
    
    
    
}
