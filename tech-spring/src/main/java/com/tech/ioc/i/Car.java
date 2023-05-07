package com.tech.ioc.i;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author lw
 * @since 2023-05-07
 */
@Component
public class Car implements InitializingBean, DisposableBean, BeanNameAware, BeanFactoryAware,BeanClassLoaderAware, ApplicationContextAware {

    @Autowired
    private Tank tank;

    public Car(){
        System.out.println("new Car");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean afterPropertiesSet");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean destroy");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("BeanNameAware setBeanName name="+name);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("BeanFactoryAware setBeanFactory beanFactory="+beanFactory.getClass());
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("BeanClassLoaderAware setBeanClassLoader="+classLoader.getClass());
    }

    @PostConstruct
    public void init(){
        System.out.println("@PostConstruct");
    }

    @PreDestroy
    public void destroys(){
        System.out.println("@PreDestroy");
    }

    public void initMethod(){
        System.out.println("@bean initMethod");
    }
    public void destroyMethod(){
        System.out.println("@bean destroyMethod");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("ApplicationContextAware ");
    }
}
