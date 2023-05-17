package com.tech.aop.b;

import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 通过FactoryBean的getObject创建代理实现,只能对单一目标Bean进行AOP,如果想把颗粒度控制在方法级别需要使用advisor,advisor包含了advice和切点
 *
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

    //Interceptor方式通知
    @Bean
    public LogInterceptor logInterceptor() {
        return new LogInterceptor();
    }

    //FactoryBean方式创建单个代理
    @Bean
    public ProxyFactoryBean calculateProxy() {
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();

        //根据指定的顺序执行
        proxyFactoryBean.setInterceptorNames("logAdvice", "logInterceptor");

        proxyFactoryBean.setTarget(simpleCaculate());
        return proxyFactoryBean;
    }
}
