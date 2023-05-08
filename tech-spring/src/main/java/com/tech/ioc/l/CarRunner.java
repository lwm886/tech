package com.tech.ioc.l;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author lw
 * @since 2023-05-08
 */
public class CarRunner {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(CarConfig.class);
//        Car bean = applicationContext.getBean(Car.class);
//        System.out.println(bean);
//
//        Tank bean1 = applicationContext.getBean(Tank.class);
//        System.out.println(bean1);
        Plane plane = applicationContext.getBean(Plane.class);
        System.out.println(plane);
    }
}
