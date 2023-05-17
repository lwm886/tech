package com.tech.aop.proxy.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author lw
 * @since 2023-05-17
 */
public class CGLibProxy implements MethodInterceptor {

    private Object bean;

    private Enhancer enhancer = new Enhancer();

    public CGLibProxy(Object bean) {
        this.bean = bean;
    }

    /**
     * 创建扩展类代理对象
     *
     * @return
     */
    public Object getProxy() {
        //为哪个类进行通过扩展类的模式创建代理代理
        if (bean.equals("noBean")) {
            //可以通过构造器传入类型
            enhancer.setSuperclass(UserService.class);
        } else {
            enhancer.setSuperclass(Dragon.class);
        }
        enhancer.setCallback(this);
        //通过字节码技术创建代理对象
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        if (bean.equals("noBean")) {
            System.out.println("为一个没有任何实现的接口凭空创建一个代理类");
            System.out.println("param= " + args[0]);
            return args[0];
        }

        String name = method.getName();
        if (name.equalsIgnoreCase("wake")) {
            System.out.println("早上好");
        }
        if (name.equalsIgnoreCase("sleep")) {
            System.out.println("晚安");
        }
        return method.invoke(bean, args);
    }
}
