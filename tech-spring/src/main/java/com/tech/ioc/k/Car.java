package com.tech.ioc.k;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * @author lw
 * @since 2023-05-07
 */
@Component
@Data
public class Car {
    private String name="default";

    @Autowired
    private Tank tank;
}
