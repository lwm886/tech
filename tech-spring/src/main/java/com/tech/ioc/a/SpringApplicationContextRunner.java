package com.tech.ioc.a;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 加载Spring上下文
 *
 * AnnotationConfigApplicationContext 加载注解配置类
 * ClassPathXmlApplicationContext 加载XML配置
 *
 * @author lw
 * @since 2023-04-21
 */
public class SpringApplicationContextRunner {
    public static void main(String[] args) {
        //使用注解配置类的方式，加载Spring上下文
        //AnnotationConfigApplicationContext构造参中的类（可以是配置类，也可以是某个Bean）可以不用加注解，如配置类可以不用加@Configuration，如Bean可以不用加@Component
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(CarConfig.class);
        Car car = applicationContext.getBean(Car.class);
        System.out.println(car);
        //还可以通过ClassPathXmlApplicationContext使用XML的方式加载Spring上下文


    }
}
