package com.tech.ioc.e;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 有Configuration，@Bean标记的对应的bean只会实例化一次，可以保证单例
 * 无Configuration，@Bean标记的对应的bean调用一次实例化一次，不能保证单例
 * @author lw
 * @since 2023-04-25
 */
@Configuration
public class SingleConfig {
    @Bean
    public Object object1(){
        System.out.println("create object1");
        return new Object();
    }

    @Bean
    public Object object2(){
        Object o1 = object1();
        Object o2 = object1();
        Object o3 = object1();
        System.out.println("create object2");
        return new Object();
    }
}
