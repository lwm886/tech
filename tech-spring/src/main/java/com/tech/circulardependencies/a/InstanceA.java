package com.tech.circulardependencies.a;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author lw
 * @since 2023-05-09
 */
@Component
@Scope("prototype")
public class InstanceA implements IApi {

    @Autowired
    private InstanceB instanceB;

    public InstanceA() {
        System.out.println("InstanceA 被实例化");
    }

    public InstanceA(InstanceB instanceB) {
        this.instanceB = instanceB;
    }

    public InstanceB getInstanceB() {
        return instanceB;
    }

    public void setInstanceB(InstanceB instanceB) {
        this.instanceB = instanceB;
    }

    @Override
    public void say() {
        System.out.println("I'm A");
    }
}
