package com.tech.circulardependencies.b;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author lw
 * @since 2023-05-11
 */
@Component
public class InstanceB {
    @Autowired
    private InstanceA instanceA;

    public InstanceB() {
        System.out.println("InstanceB 被实例化");
    }
}
