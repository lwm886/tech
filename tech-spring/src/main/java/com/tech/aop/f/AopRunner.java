package com.tech.aop.f;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author lw
 * @since 2023-05-17
 */
public class AopRunner {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AopConfig.class);
        Calculate simpleCaculate = applicationContext.getBean("simpleCaculate", Calculate.class);
        simpleCaculate.add(1,5);
    }
}
