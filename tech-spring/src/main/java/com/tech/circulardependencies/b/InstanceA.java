package com.tech.circulardependencies.b;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author lw
 * @since 2023-05-11
 */
@Component
public class InstanceA {

    @Autowired
    private InstanceB instanceB;

    public InstanceA() {
        System.out.println("InstanceA 被实例化");
    }
}
