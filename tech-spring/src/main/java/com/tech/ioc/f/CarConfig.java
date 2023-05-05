package com.tech.ioc.f;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lw
 * @since 2023-04-27
 */
@Configuration
public class CarConfig {

    @Bean
    public Car car(){
        return new Car();
    }

}
