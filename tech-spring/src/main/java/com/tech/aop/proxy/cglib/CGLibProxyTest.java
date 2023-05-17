package com.tech.aop.proxy.cglib;

import org.springframework.context.ApplicationContext;

/**
 * CGLIB动态代理：
 * Spring在5之前默认使用JDK动态代理，5及之后默认使用CGLIB动态代理，CGLIB动态代理底层使用的是ASM字节码处理框架，可以通过字节码处理技术动态的为一个类通过子类模式创建代理对象。它具有这么一些优点：JDK动态代理只能对接口代理，代理对象只能赋值给接口，CGLIB动态代理既能为接口创建代理对象又能为普通的类创建代理对象，代理对象可以赋值给接口也可以赋值给类，CGLIB代理对象的性能比JDK动态代理得到的代理对象的性能要高。
 * @author lw
 * @since 2023-05-17
 */
public class CGLibProxyTest {
    public static void main(String[] args) {
        CGLibProxy cgLibProxy = new CGLibProxy(new Dragon());
        Dragon proxy = (Dragon) cgLibProxy.getProxy();
        proxy.wake();
        proxy.sleep();

        cgLibProxy = new CGLibProxy("noBean");
        UserService userService = (UserService) cgLibProxy.getProxy();
        int query = userService.query(100);
        System.out.println(query);
    }
}
