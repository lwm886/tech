package com.tech.ioc.o;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author lw
 * @since 2023-05-09
 */
@Component("car")
@Data
public class Car {
    private String name="car";
}
