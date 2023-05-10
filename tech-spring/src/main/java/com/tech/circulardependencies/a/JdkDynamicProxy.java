package com.tech.circulardependencies.a;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author lw
 * @since 2023-05-10
 */
public class JdkDynamicProxy implements InvocationHandler {

    private Object target;

    public JdkDynamicProxy(Object target) {
        this.target = target;
    }

    public <T> T getProxy() {
        return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("测试");
        return  method.invoke(target,args);
    }

//    public static void main(String[] args) throws Throwable {
//        JdkDynamicProxy jdkDynamicProxy = new JdkDynamicProxy(new InstanceA());
//        Object proxy = jdkDynamicProxy.getProxy();
//        jdkDynamicProxy.invoke(proxy,InstanceA.class.getMethod("say",null),null);
//    }
}
