package com.tech.aop.proxy.jdk;

/**
 * @author lw
 * @since 2023-05-16
 */
public class Dragon implements Animal {
    @Override
    public void wake() {
        System.out.println("dragon wake");
    }

    @Override
    public void sleep() {
        System.out.println("dragon sleep");
    }
}
