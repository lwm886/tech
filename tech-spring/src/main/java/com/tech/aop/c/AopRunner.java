package com.tech.aop.c;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


/**
 * @author lw
 * @since 2023-05-17
 */
public class AopRunner {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AopConfig.class);
        Calculate bean =applicationContext.getBean("caculateProxy",Calculate.class);
        int add = bean.add(1, 5);
        System.out.println(add);
        int multi = bean.multi(1, 5);
        System.out.println(multi);


    }
}
