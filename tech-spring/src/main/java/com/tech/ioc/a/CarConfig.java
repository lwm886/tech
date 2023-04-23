package com.tech.ioc.a;

import org.springframework.context.annotation.Bean;

/**
 * @author lw
 * @since 2023-04-22
 */
//@Configuration
public class CarConfig {

    @Bean(name = "car")
    public Car getCar(){
        return new Car();
    }
}


