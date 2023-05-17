package com.tech.aop.b;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author lw
 * @since 2023-05-17
 */
public class AopRunner {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AopConfig.class);
        Calculate calculateProxy = applicationContext.getBean("calculateProxy", Calculate.class);
        System.out.println(calculateProxy.add(1,5));
    }
}
