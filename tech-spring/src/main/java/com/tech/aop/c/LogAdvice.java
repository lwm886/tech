package com.tech.aop.c;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author lw
 * @since 2023-05-17
 */
public class LogAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object o) throws Throwable {
        String name = method.getName();
        System.out.println("执行目标方法【"+name+"】的<前置通知>，入参"+ Arrays.asList(args));
    }
}
