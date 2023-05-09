package com.tech.ioc.o;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author lw
 * @since 2023-05-09
 */
public class CarRunner {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(CarConfig.class);
        Object car = applicationContext.getBean("car");
        System.out.println(car);
    }
}
