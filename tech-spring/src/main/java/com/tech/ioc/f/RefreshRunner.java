package com.tech.ioc.f;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author lw
 * @since 2023-04-27
 */
public class RefreshRunner {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(CarConfig.class);
        Car bean = applicationContext.getBean(Car.class);
        System.out.println(bean);
    }
}
