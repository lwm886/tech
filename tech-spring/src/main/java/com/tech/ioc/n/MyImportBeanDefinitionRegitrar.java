package com.tech.ioc.n;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author lw
 * @since 2023-05-09
 */
public class MyImportBeanDefinitionRegitrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        RootBeanDefinition beanDefinition = new RootBeanDefinition();
        beanDefinition.setBeanClass(Plane.class);
        beanDefinition.getPropertyValues().add("name","飞机");
        registry.registerBeanDefinition("plane",beanDefinition);
    }
}
