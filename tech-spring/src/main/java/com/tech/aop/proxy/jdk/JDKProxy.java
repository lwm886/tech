package com.tech.aop.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * JDK动态代理
 * 在程序执行过程中，通过Java反射技术动态生成代理代理对象，不用手动创建代理类，这种方式简洁优雅。JDK动态代理生成的代理对象只能赋值给接口，使用JDK动态代理只需要实现InvocationHandler接口，在invoke方法中为接口的方法进行增强处理，使用这一个实现类就可以为多个接口创建动态代理，它不仅可以为接口的实现类创建代理对象，甚至可以为接口凭空创建代理对象作为实现类对象。
 *
 * 在项目中有一个场景，定义了多个yaml规则文件，需要为每个yaml规则文件生成代理对象用于处理规则，当时是这样处理的，事先定义一个处理接口，扫描每个规则文件，将扫描的数据封装成规则对象，然后通过JDK动态代理为接口创建代理对象，创建代理对象时将规则对象传入到InvocationHandler中，然后所有的规则统一在invoke中进行处理，然后将这些代理对象注册到容器中，用于上层应用。不用手动去创建处理规则的类，这种动态代理的方式更加简洁优雅。
 *
 * @author lw
 * @since 2023-05-16
 */
public class JDKProxy implements InvocationHandler {

    private Object bean;

    public JDKProxy(Object bean) {
        this.bean = bean;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equals("wake")) {
            System.out.println("早上好");
        }
        if (method.getName().equals("sleep")) {
            System.out.println("晚安");
        }
        return method.invoke(bean, args);
    }
}
