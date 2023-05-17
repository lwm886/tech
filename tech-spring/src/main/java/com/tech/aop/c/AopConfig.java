package com.tech.aop.c;

import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.NameMatchMethodPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lw
 * @since 2023-05-17
 */
@Configuration
public class AopConfig {
    //被代理Bean
    @Bean
    public Calculate simpleCaculate() {
        return new SimpleCaculate();
    }

    //advice方式通知
    @Bean
    public LogAdvice logAdvice() {
        return new LogAdvice();
    }

    //advisor 指定advice和切点
    @Bean
    public NameMatchMethodPointcutAdvisor logAspect(){
        NameMatchMethodPointcutAdvisor advisor = new NameMatchMethodPointcutAdvisor();
        //通知（增强逻辑）
        advisor.setAdvice(logAdvice());
        //指定切点
        advisor.setMappedName("multi");
        return advisor;
    }

    @Bean
    public ProxyFactoryBean caculateProxy(){
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setInterceptorNames("logAspect");
        proxyFactoryBean.setTarget(simpleCaculate());
        return proxyFactoryBean;
    }

}
