package com.tech.aop.d;

import com.tech.aop.b.LogInterceptor;
import com.tech.aop.c.LogAdvice;
import org.aopalliance.intercept.MethodInterceptor;

import java.util.ArrayList;
import java.util.List;

/**
 * Spring在织入多个增强逻辑时使用了责任链模式
 * 所有的责任对象需要实现统一接口，放到一个list里，通过循环或递归的方式对责任对象进行调用
 *
 *
 * @author lw
 * @since 2023-05-17
 */
public class Runner {
    public static void main(String[] args) throws Throwable {
        //把一条链上的都初始化
        List<MethodInterceptor> list = new ArrayList();
        list.add(new MethodBeforeAdviceInterceptor(new LogAdvice()));
        list.add(new LogInterceptor());

        MyMethodInvocation invocation = new MyMethodInvocation(list);
        invocation.proceed();
    }
}
