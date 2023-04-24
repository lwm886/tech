package com.tech.ioc.c;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 通过应用上下文指定Scan路径
 * @author lw
 * @since 2023-04-24
 */
public class CarRunner {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
        applicationContext.scan("com.tech.ioc.c");
        Car bean = applicationContext.getBean(Car.class);
        System.out.println(bean);
    }
}
