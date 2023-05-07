package com.tech.ioc.h;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

/**
 * @author lw
 * @since 2023-05-06
 */
@Component
@Data
public class Car implements InitializingBean {
    private String name="default";

    @Autowired
    private Tank tank;

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
