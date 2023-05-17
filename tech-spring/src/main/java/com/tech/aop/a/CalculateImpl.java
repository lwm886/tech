package com.tech.aop.a;

import org.springframework.stereotype.Component;

/**
 * @author lw
 * @since 2023-05-17
 */
@Component
public class CalculateImpl implements Calculate {

    @Override
    public int add(int a, int b) {
        System.out.println("执行目标方法:add");
        return a+b;
    }
}
