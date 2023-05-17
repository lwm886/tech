package com.tech.aop.proxy.jdk1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author lw
 * @since 2023-05-16
 */
public class JDKProxy implements InvocationHandler {

    private Object data;

    public JDKProxy(Object data) {
        this.data = data;
    }

    @Override

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        int arg = (int)args[0];
        int arg1 = (int)args[1];
        int res=0;
        if(data.equals("SUM")){
            res=arg+arg1;
        }else if(data.equals("MULTI")){
            res=arg*arg1;
        }
        System.out.println("invoke "+data+" result = "+res);
        return res;
    }
}
