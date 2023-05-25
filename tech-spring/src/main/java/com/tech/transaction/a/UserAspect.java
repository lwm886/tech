package com.tech.transaction.a;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author lw
 * @since 2023-05-17
 */
@Aspect
@Component
public class UserAspect {

    @Pointcut("execution(* com.tech.transaction.a.UserService.*(..))")
    public void pointCut() {
    }


    @After(value = "pointCut()")
    public void methodAfter(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        System.out.println("执行目标方法【" + name + "】的<后置通知>,入参" + Arrays.asList(joinPoint.getArgs()));
    }


    @Before(value = "pointCut()")
    public void methodBefore(JoinPoint joinPoint) throws Throwable {
        String name = joinPoint.getSignature().getName();
        System.out.println("执行目标方法【" + name + "】的<前置通知>,入参" + Arrays.asList(joinPoint.getArgs()));
    }

}
