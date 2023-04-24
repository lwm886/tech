package com.tech.ioc.b;

import org.springframework.context.annotation.Bean;

/**
 * @author lw
 * @since 2023-04-23
 */
public class CarConfig {

    @Bean
    public Car getCar(){
        return new Car();
    }
}
