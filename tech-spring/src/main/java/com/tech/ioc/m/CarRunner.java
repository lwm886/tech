package com.tech.ioc.m;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author lw
 * @since 2023-05-09
 */
public class CarRunner {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(CarConfig.class);
        Object bean = applicationContext.getBean("car");
        System.out.println(bean);
    }
}
