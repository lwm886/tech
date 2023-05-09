package com.tech.ioc.n;

import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author lw
 * @since 2023-05-09
 */
public class CarRunner {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(CarConfig.class);
        Object bean = applicationContext.getBean(Plane.class);
        System.out.println(bean);
    }
}
