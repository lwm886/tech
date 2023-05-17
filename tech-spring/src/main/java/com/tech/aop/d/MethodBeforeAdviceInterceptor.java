package com.tech.aop.d;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.MethodBeforeAdvice;

/**
 * @author lw
 * @since 2023-05-17
 */
public class MethodBeforeAdviceInterceptor implements MethodInterceptor {

    //前置通知
    MethodBeforeAdvice methodBeforeAdvice;

    public MethodBeforeAdviceInterceptor(MethodBeforeAdvice methodBeforeAdvice) {
        this.methodBeforeAdvice = methodBeforeAdvice;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        methodBeforeAdvice.before(invocation.getMethod(),invocation.getArguments(),invocation.getThis());
        return invocation.proceed();
    }
}
