package com.tech.ioc.i;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author lw
 * @since 2023-05-07
 */
@Configuration
@ComponentScan
public class CarCfg {

    @Bean(initMethod = "initMethod",destroyMethod = "destroyMethod")
    public Car car(){
        return new Car();
    }
}
