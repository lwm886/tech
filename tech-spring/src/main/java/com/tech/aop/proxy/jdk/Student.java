package com.tech.aop.proxy.jdk;

/**
 * @author lw
 * @since 2023-05-16
 */
public class Student implements Person {
    @Override
    public void wake() {
        System.out.println("student wake");
    }

    @Override
    public void sleep() {
        System.out.println("student sleep");
    }
}
