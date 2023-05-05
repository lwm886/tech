package com.tech.ioc.f;

import lombok.Data;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;

/**
 * @author lw
 * @since 2023-04-27
 */
@Data
public class Car {
    private String name="default";

    @Autowired
    private BeanFactory beanFactory;

    @Autowired
    private ResourceLoaderAware resourceLoaderAware;
}
