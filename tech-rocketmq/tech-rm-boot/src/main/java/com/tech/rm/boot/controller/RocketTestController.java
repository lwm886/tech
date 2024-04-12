package com.tech.rm.boot.controller;

import com.tech.rm.boot.basic.Producer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author lw
 * @since 2024/4/12
 */
@RestController
@RequestMapping("/mq")
public class RocketTestController {
    
    @Resource
    Producer producer;
    
    private static final String TOPIC="BootTestTopic-x100:tag0";
    //发送普通消息
    @RequestMapping("/send")
    public String sendMsg(String msg){
        producer.sendMessage(TOPIC,msg);
        return "send ok";
    }
    //发送半事务消息
    @GetMapping("sendT")
    public String sendMsgInTransaction(String msg) throws InterruptedException {
        producer.sendMessageInTransaction(TOPIC,msg);
        return "send ok";
    }
}
