package com.tech.ioc.b;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author lw
 * @since 2023-04-23
 */
public class CarRunner {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(CarConfig.class);
        Car bean = applicationContext.getBean(Car.class);
        System.out.println(bean);
    }
}
