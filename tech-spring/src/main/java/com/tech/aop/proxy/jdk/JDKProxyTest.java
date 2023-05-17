package com.tech.aop.proxy.jdk;

import java.lang.reflect.Proxy;

/**
 * @author lw
 * @since 2023-05-16
 */
public class JDKProxyTest {
    public static void main(String[] args) {
        JDKProxy jdkProxy = new JDKProxy(new Student());
        Person personProxy = (Person) Proxy.newProxyInstance(jdkProxy.getClass().getClassLoader(), new Class[]{Person.class}, jdkProxy);
        personProxy.wake();
        personProxy.sleep();

        jdkProxy=new JDKProxy(new Dragon());
        Animal animal = (Animal) Proxy.newProxyInstance(jdkProxy.getClass().getClassLoader(), new Class[]{Animal.class}, jdkProxy);
        animal.wake();
        animal.sleep();
    }
}
