package com.tech.ioc.h;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author lw
 * @since 2023-05-06
 */
public class InstantiationAwareBeanPostProcessorRunner {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(CarConfig.class);
        Car bean = applicationContext.getBean(Car.class);
        System.out.println(bean);

    }
}
