package com.tech.ioc.g;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author lw
 * @since 2023-05-04
 */
public class FactoryBeanRunner {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Car.class);
//        Car bean = (Car) applicationContext.getBean("&car");
//        System.out.println(bean);
        Tank bean = (Tank) applicationContext.getBean("car");
        System.out.println(bean);
    }
}
