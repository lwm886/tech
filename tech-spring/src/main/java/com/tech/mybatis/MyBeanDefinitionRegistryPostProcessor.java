package com.tech.mybatis;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;

import java.util.HashSet;
import java.util.Set;

/**
 * 重写BeanDefinitionScanner的是否候选组件函数，让Mapper接口被扫描到
 * 经过扫描后得到Mapper接口列表
 * 
 * BeanDefinitionRegistryPostProcessor后置处理器进行批量注册Bean定义，但需要注册接口的代理，所以需要引入FactoryBean，在它的getObject中返回代理对象注入到spring容器
 * 
 * @author lw
 * @since 2023/6/7
 */
public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {
    
    private Set<Class> mapperInterFaces=new HashSet<>();
    
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
       for (Class c:mapperInterFaces){
           RootBeanDefinition rootBeanDefinition = new RootBeanDefinition();
           rootBeanDefinition.setBeanClass(MyFactoryBean.class);
           rootBeanDefinition.getPropertyValues().add("mapperInterFace",c);
           rootBeanDefinition.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);
           beanDefinitionRegistry.registerBeanDefinition("myUserMapper",rootBeanDefinition);
       }
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }
}
