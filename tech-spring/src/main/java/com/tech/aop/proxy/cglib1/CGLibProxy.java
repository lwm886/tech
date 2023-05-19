package com.tech.aop.proxy.cglib1;


import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author lw
 * @since 2023-05-19
 */
public class CGLibProxy implements MethodInterceptor {

    private Object bean;

    private Enhancer enhancer = new Enhancer();

    public CGLibProxy(Object bean) {
        this.bean = bean;
    }

    public Object getProxy() {
        enhancer.setSuperclass(UserServiceImpl.class);
        enhancer.setCallback(this);
        return enhancer.create();
    }

    /**
     * @param proxy 代理对象
     * @param method 被代理方法
     * @param args 被代理方法参数
     * @param methodProxy 代理方法
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("proxy... "+method.getName());
        //代理方法调用invokeSuper，如果存在方法内部调用，会使用代理对象进行调用
        return methodProxy.invokeSuper(proxy,args);
    }
}
