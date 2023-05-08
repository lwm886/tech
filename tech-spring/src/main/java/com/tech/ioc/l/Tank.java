package com.tech.ioc.l;

import lombok.Data;

/**
 * @author lw
 * @since 2023-05-08
 */
@Data
public class Tank {
    public Tank(){
        System.out.println("tank 被加载。。。");
    }
}
