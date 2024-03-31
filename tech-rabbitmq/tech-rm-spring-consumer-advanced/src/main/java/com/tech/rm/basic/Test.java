package com.tech.rm.basic;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author lw
 * @since 2024/3/30
 */
public class Test {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring-rabbitmq-consumer.xml");
    }
}
