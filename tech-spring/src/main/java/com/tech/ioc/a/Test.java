package com.tech.ioc.a;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author lw
 * @since 2023-04-22
 */
public class Test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        System.out.println(applicationContext instanceof BeanFactory);
    }
}
