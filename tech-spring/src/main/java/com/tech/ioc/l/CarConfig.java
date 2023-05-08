package com.tech.ioc.l;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author lw
 * @since 2023-05-08
 */
@Configuration
@ComponentScan
public class CarConfig {

    @Bean
    public Car car(){
        Car car = new Car();
        car.setTank(tank());
        return car;
    }

    @Bean
    public Tank tank(){
        return new Tank();
    }
}
