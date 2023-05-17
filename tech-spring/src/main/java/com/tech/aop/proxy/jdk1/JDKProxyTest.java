package com.tech.aop.proxy.jdk1;

import java.lang.reflect.Proxy;

/**
 * @author lw
 * @since 2023-05-16
 */
public class JDKProxyTest {
    public static void main(String[] args) {
        JDKProxy proxy = new JDKProxy("SUM");
        Calcutor calcutor = (Calcutor) Proxy.newProxyInstance(proxy.getClass().getClassLoader(), new Class[]{Calcutor.class}, proxy);
        int cal = calcutor.cal(1, 5);
        System.out.println(cal);

        proxy = new JDKProxy("MULTI");
        calcutor = (Calcutor) Proxy.newProxyInstance(proxy.getClass().getClassLoader(), new Class[]{Calcutor.class}, proxy);
        cal = calcutor.cal(1, 5);
        System.out.println(cal);


    }
}
