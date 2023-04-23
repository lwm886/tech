package com.tech.ioc.a;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author lw
 * @since 2023-04-22
 */
@Component
public class MyBeanDefinitionRegistryPostProcessorRunner {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ConfigScan.class);


//        Tank bean = applicationContext.getBean(Tank.class);
        Car bean = applicationContext.getBean(Car.class);
        System.out.println(bean);
    }
}
