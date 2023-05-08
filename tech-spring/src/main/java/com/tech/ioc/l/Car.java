package com.tech.ioc.l;

import lombok.Data;

/**
 * @author lw
 * @since 2023-05-08
 */
@Data
public class Car {

    private Tank tank;

    public Car() {
        System.out.println("car 被加载。。。");
    }
}
