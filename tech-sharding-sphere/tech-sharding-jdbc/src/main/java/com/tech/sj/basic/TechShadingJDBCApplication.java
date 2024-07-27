package com.tech.sj.basic;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan(basePackages = {"com.tech.sj.basic.mapper"})
@SpringBootApplication
public class TechShadingJDBCApplication {

    public static void main(String[] args) {
        SpringApplication.run(TechShadingJDBCApplication.class, args);
    }

}
