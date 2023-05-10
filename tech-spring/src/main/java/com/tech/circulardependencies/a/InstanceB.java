package com.tech.circulardependencies.a;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author lw
 * @since 2023-05-09
 */
public class InstanceB{

    @Autowired
    private InstanceA instanceA;

    public InstanceB() {
        System.out.println("InstanceB实例化");
    }

    public InstanceB(InstanceA instanceA) {
        this.instanceA = instanceA;
    }

    public InstanceA getInstanceA() {
        return instanceA;
    }

    public void setInstanceA(InstanceA instanceA) {
        this.instanceA = instanceA;
    }

}
