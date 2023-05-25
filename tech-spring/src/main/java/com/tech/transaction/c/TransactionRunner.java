package com.tech.transaction.c;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author lw
 * @since 2023-05-25
 */
public class TransactionRunner {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AopConfig.class);
        UserService userService = applicationContext.getBean(UserService.class);
        userService.save();
    }
}
