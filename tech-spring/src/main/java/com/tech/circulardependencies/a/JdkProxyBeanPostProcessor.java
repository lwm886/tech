package com.tech.circulardependencies.a;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.SmartInstantiationAwareBeanPostProcessor;

/**
 * @author lw
 * @since 2023-05-10
 */
public class JdkProxyBeanPostProcessor implements SmartInstantiationAwareBeanPostProcessor {
    @Override
    public Object getEarlyBeanReference(Object bean, String beanName) throws BeansException {
        if(bean instanceof  InstanceA){
            JdkDynamicProxy jdkDynamicProxy = new JdkDynamicProxy(bean);
            return jdkDynamicProxy.getProxy();
        }
        return bean;
    }
}
