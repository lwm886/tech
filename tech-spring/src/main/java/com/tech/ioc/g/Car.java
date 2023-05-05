package com.tech.ioc.g;

import lombok.Data;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * @author lw
 * @since 2023-05-04
 */
@Data
public class Car implements FactoryBean<Tank> {
    private String name="default";

    @Override
    public Tank getObject() throws Exception {
        return new Tank();
    }

    @Override
    public Class<?> getObjectType() {
        return Tank.class;
    }
}
