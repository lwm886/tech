package com.tech.ioc.m;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author lw
 * @since 2023-05-09
 */
//@Import(value = Tank.class)
@Import(value = ServiceImportSelector.class)
@ComponentScan(basePackages = "com.tech.ioc.m")
@Configuration
public class CarConfig {

//    @Bean("car")
    public Tank tank() {
        return new Tank();
    }
}
