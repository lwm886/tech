package com.tech.rm.boot.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.Objects;

/**
 * @author lw
 * @since 2024/4/2
 */
@RequestMapping("/mq")
@RestController
public class ProducerController {
    
    @Resource
    RabbitTemplate rabbitTemplate;
    
    @ApiOperation(value = "直连模式发送消息",notes = "直连模式发送消息到队列")
    @RequestMapping("/hw")
    public Object hw(String msg) throws UnsupportedEncodingException {
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN);
        rabbitTemplate.convertAndSend("queue_hw",new Message(msg.getBytes("UTF-8"),messageProperties));
        return "message send "+msg;
    }
    
    @ApiOperation(value = "工作队列模式发送消息",notes = "工作队列模式发送消息到队列")
    @RequestMapping("/wq")
    public Object wq(String msg) throws UnsupportedEncodingException {
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN);
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("queue_work_queue",new Message((i+msg).getBytes("UTF-8"),messageProperties));
        }
        return "message send "+msg;
    }

    @ApiOperation(value = "发布订阅模式发送消息",notes = "发布订阅模式发送消息到队列")
    @RequestMapping("/ps")
    public Object ps(String msg) throws UnsupportedEncodingException {
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN);
        rabbitTemplate.convertAndSend("fanoutExchange","",new Message(msg.getBytes("UTF-8"),messageProperties));
        return "message send "+msg;
    }
    
    @ApiOperation(value = "路由模式发送消息",notes = "路由模式发送消息到队列")
    @RequestMapping("/direct")
    public Object direct(String msg,String routingKey) throws UnsupportedEncodingException {
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN);
        rabbitTemplate.convertAndSend("directExchange",routingKey,new Message(msg.getBytes("UTF-8"),messageProperties));
        return "message send "+msg;
    }
    
    @ApiOperation(value = "TOPIC模式发送消息",notes = "TOPIC模式发送消息到队列")
    @RequestMapping("/topic")
    public Object topic(String msg,String routingKey) throws UnsupportedEncodingException {
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN);
        rabbitTemplate.convertAndSend("topicExchange",routingKey,new Message(msg.getBytes("UTF-8"),messageProperties));
        return "message send "+msg;
    }
    
    
}
