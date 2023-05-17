package com.tech.aop.f;

import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.aop.support.RegexpMethodPointcutAdvisor;
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

    @Bean
    public LogInterceptor logInterceptor() {
        return new LogInterceptor();
    }
    //正则匹配切点
    @Bean
    public RegexpMethodPointcutAdvisor regexpMethodPointcutAdvisor(){
        RegexpMethodPointcutAdvisor advisor = new RegexpMethodPointcutAdvisor();
        advisor.setAdvice(logInterceptor());
        advisor.setPattern("com.tech.aop.f.SimpleCaculate.*");
        return advisor;
    }

    //使用所有的advisor生效
    @Bean
    public DefaultAdvisorAutoProxyCreator autoProxyCreator(){
        return new DefaultAdvisorAutoProxyCreator();
    }
}
