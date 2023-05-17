package com.tech.aop.e;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


/**
 * @author lw
 * @since 2023-05-17
 */
public class AopRunner {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AopConfig.class);
        Calculate bean =applicationContext.getBean("simpleCaculate",Calculate.class);
        int add = bean.add(1, 5);
        System.out.println(add);



    }
}
