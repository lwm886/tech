package com.tech.aop.d;

import com.tech.aop.c.Calculate;
import com.tech.aop.c.SimpleCaculate;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author lw
 * @since 2023-05-17
 */
public class MyMethodInvocation implements MethodInvocation {

    public List<MethodInterceptor> list;
    public Calculate target;
    int i=0;

    @Override
    public Object proceed() throws Throwable {
        if(i==list.size()){
            return target.add(1,6);
        }
        MethodInterceptor methodInterceptor = list.get(i++);
        return methodInterceptor.invoke(this);
    }


    public MyMethodInvocation(List<MethodInterceptor> list) {
        this.list = list;
        this.target=new SimpleCaculate();
    }

    @Override
    public Method getMethod() {
        return null;
    }

    @Override
    public Object[] getArguments() {
        return new Object[0];
    }

    @Override
    public Object getThis() {
        return null;
    }

    @Override
    public AccessibleObject getStaticPart() {
        return null;
    }
}
