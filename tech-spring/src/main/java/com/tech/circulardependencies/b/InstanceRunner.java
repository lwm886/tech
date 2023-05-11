package com.tech.circulardependencies.b;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author lw
 * @since 2023-05-11
 */
public class InstanceRunner {
    public static void main(String[] args) {
        //禁用循环依赖
//        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
//        applicationContext.setAllowCircularReferences(false);
//        applicationContext.register(InstanceConfig.class);
//        applicationContext.refresh();
//        InstanceA beanA = applicationContext.getBean(InstanceA.class);
//        System.out.println(beanA);
//        InstanceB beanB = applicationContext.getBean(InstanceB.class);
//        System.out.println(beanB);

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(InstanceConfig.class);
        InstanceA instanceA = applicationContext.getBean(InstanceA.class);
        System.out.println(instanceA);
//        System.out.println(instanceA);
//        InstanceB instanceB = applicationContext.getBean(InstanceB.class);
//        System.out.println(instanceB);


    }
}
