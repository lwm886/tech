package com.tech.aop.e;

import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
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

    //通过模式匹配多个类 创建代理
    @Bean
    public BeanNameAutoProxyCreator autoProxyCreator(){
        BeanNameAutoProxyCreator beanNameAutoProxyCreator = new BeanNameAutoProxyCreator();
        //设置要创建代理的那些Bean的名称
        beanNameAutoProxyCreator.setBeanNames("*Caculate");
        //设置拦截器名称 有顺序
        beanNameAutoProxyCreator.setInterceptorNames("logInterceptor");
        return beanNameAutoProxyCreator;
    }


}
