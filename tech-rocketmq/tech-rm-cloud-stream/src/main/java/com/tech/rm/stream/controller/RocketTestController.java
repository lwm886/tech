package com.tech.rm.stream.controller;

import com.tech.rm.stream.basic.ScProducer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author lw
 * @since 2024/4/12
 */
@RestController
public class RocketTestController {
    
    @Resource
    ScProducer scProducer;
    
    //发送消息
    @RequestMapping("/send")
    public String sendMsg(String msg){
        scProducer.sendMessage(msg);
        return "send ok";
    }
  
}
