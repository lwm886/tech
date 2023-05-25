package com.tech.transaction.a;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author lw
 * @since 2023-05-22
 */
public class UserRunner {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AopConfig.class);
        UserService bean = applicationContext.getBean(UserService.class);
        bean.save();
    }
}
