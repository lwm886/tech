package com.tech.ioc.a;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author lw
 * @since 2023-04-22
 */
public class MyInstantiationAwareBeanPostProcessorRunner {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyInstantiationAwareBeanPostProcessor.class,Car.class);
        Car bean = applicationContext.getBean(Car.class);
        System.out.println(bean);
    }
}
