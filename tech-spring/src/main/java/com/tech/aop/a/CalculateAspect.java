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
    //在目标类中引入一个类,通过bean名称获取Bean时，可以获取引入的这个Bean的代理也可以获取原Bean的代理,可以根据需要的类型进行强制转换，引入的这个Bean不会被增强处理
    //value 动态实现的类
    //defaultImpl 引入接口的默认实现
    //注解标记的属性 因为的接口
//    @DeclareParents(value = "com.tech.aop.a.CalculateImpl",
//            defaultImpl = ImportCalculateImpl.class)
//    public static ImportCalculate importCalculate;

    @Pointcut("execution(* com.tech.aop.a.Calculate.*(..))")
    public void pointCut() {
    }

    public void test1(){
        System.out.println(1);
    }

    @Before(value = "pointCut()")
    public void methodBefore(JoinPoint joinPoint) throws Throwable {
        String name = joinPoint.getSignature().getName();
        System.out.println("执行目标方法【" + name + "】的<前置通知>,入参" + Arrays.asList(joinPoint.getArgs()));
    }

    @After(value = "pointCut()")
    public void methodAfter(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        System.out.println("执行目标方法【" + name + "】的<后置通知>,入参" + Arrays.asList(joinPoint.getArgs()));
    }

    @AfterReturning(value = "pointCut()", returning = "result")
    public void methodAfter(JoinPoint joinPoint, Object result) {
        String name = joinPoint.getSignature().getName();
        System.out.println("执行目标方法【" + name + "】的<返回通知>,入参" + Arrays.asList(joinPoint.getArgs()));
    }

    @AfterThrowing(value = "pointCut()")
    public void methodThrowing(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        System.out.println("执行目标方法【" + name + "】的<异常通知>,入参" + Arrays.asList(joinPoint.getArgs()));
    }
}
