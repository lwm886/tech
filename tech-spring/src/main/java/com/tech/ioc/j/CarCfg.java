package com.tech.ioc.j;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author lw
 * @since 2023-05-07
 */
@Configuration
public class CarCfg {

    @Scope(value = "prototype")
    @Bean
    public Car car(){
        return new Car();
    }
}
