package com.tech.aop.b;

import org.springframework.stereotype.Component;

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
}
