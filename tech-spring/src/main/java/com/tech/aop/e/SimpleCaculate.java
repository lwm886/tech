package com.tech.aop.e;

/**
 * @author lw
 * @since 2023-05-17
 */
public class SimpleCaculate implements Calculate {

    @Override
    public int add(int a, int b) {
        System.out.println("执行目标方法 add");
        return a+b;
    }

    @Override
    public int multi(int a, int b) {
        System.out.println("执行目标方法 multi");
        return a*b;
    }
}
