package com.tech.ioc.e;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author lw
 * @since 2023-04-25
 */
public class ConfigRunner {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SingleConfig.class);
        Object object2 = applicationContext.getBean("object2");
    }
}
