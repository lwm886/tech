package com.tech.ioc.a;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.stereotype.Component;

/**
 * BeanDefinitionRegistry后置处理器，继承BeanFactory后置处理器
 * 它们是BeanDefinition注册到BeanDefinition容器后，BeanFactory实例化Bean前执行，前者在后者之前执行
 * BeanDefinitionRegistry后置处理器可以获取BeanDefinitionRegistry，可以实现自定义注册BeanDefinition到BeanDefinition容器
 * BeanFactory后置处理器可以获取BeanFactory，可以获取BeanDefinition容器中的BeanDefinition进行修改
 *
 * @author lw
 * @since 2023-04-22
 */
@Component
public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

    /**
     * 先执行
     * 可以注册BeanDefinition到BeanDefinition容器
     * @param beanDefinitionRegistry
     * @throws BeansException
     */
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        //BeanDefinitionRegistryPostProcessor 注册Bean到BeanDefinition容器
        RootBeanDefinition beanDefinition = new RootBeanDefinition();
        beanDefinition.setBeanClass(Tank.class);
        beanDefinitionRegistry.registerBeanDefinition("tank",beanDefinition);
        System.out.println("BeanDefinitionRegistryPostProcessor registry tank");
    }

    /**
     * 后执行 可以获取BeanDefinition进行修改
     * @param beanFactory
     * @throws BeansException
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        RootBeanDefinition tankBeanDefinition = (RootBeanDefinition)beanFactory.getBeanDefinition("tank");
        //修改bean类型
        tankBeanDefinition.setBeanClass(Car.class);
        System.out.println("修改tank为car");
        //修改bean属性，name不存在添加 存在覆盖
        tankBeanDefinition.getPropertyValues().add("name1","tank改的car");
    }

}
