package com.tech.aop.a;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author lw
 * @since 2023-05-17
 */
@Aspect
@Component
public class CalculateAspect {

    @Pointcut("execution(* com.tech.aop.a.Calculate.*(..))")
    public void pointCut(){}

    @Before(value = "pointCut()")
    public void methodBefore(JoinPoint joinPoint) throws Throwable{
        String name = joinPoint.getSignature().getName();
        System.out.println("执行目标方法【"+name+"】的<前置通知>,入参"+ Arrays.asList(joinPoint.getArgs()));
    }

    @After(value = "pointCut()")
    public void methodAfter(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        System.out.println("执行目标方法【"+name+"】的<后置通知>,入参"+ Arrays.asList(joinPoint.getArgs()));
    }

    @AfterReturning(value = "pointCut()",returning = "result")
    public void methodAfter(JoinPoint joinPoint,Object result) {
        String name = joinPoint.getSignature().getName();
        System.out.println("执行目标方法【"+name+"】的<返回通知>,入参"+ Arrays.asList(joinPoint.getArgs()));
    }

    @AfterThrowing(value = "pointCut()")
    public void methodThrowing(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        System.out.println("执行目标方法【"+name+"】的<异常通知>,入参"+ Arrays.asList(joinPoint.getArgs()));
    }
}
