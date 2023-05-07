package com.tech.ioc.j;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import sun.java2d.pipe.AAShapePipe;

/**
 * @author lw
 * @since 2023-05-07
 */
public class CarRunner {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(CarCfg.class);
        Car bean = applicationContext.getBean(Car.class);
        System.out.println(bean);
    }
}
