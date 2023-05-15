package com.tech.event.b;

import com.tech.circulardependencies.b.InstanceConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author lw
 * @since 2023-05-15
 */
public class InnerEventRunner {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(InnerEventConfig.class);
        System.out.println("init:" +applicationContext.getBean(Car.class));
        applicationContext.stop();
        System.out.println("after stop:"+ applicationContext.getBean(Car.class));

        applicationContext.start();
        System.out.println("after start:"+ applicationContext.getBean(Car.class));
        //close会销毁所有单例Bean
        applicationContext.close();

    }
}
