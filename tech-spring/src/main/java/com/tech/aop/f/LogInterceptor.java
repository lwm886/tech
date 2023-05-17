package com.tech.aop.f;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author lw
 * @since 2023-05-17
 */
public class LogInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println(getClass()+"调用方法前");
        Object ret = invocation.proceed();
        System.out.println(getClass()+"调用方法后");
        return ret;
    }
}
