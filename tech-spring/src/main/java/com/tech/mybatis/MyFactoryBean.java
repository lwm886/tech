package com.tech.mybatis;

import com.tech.aop.proxy.jdk.JDKProxy;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author lw
 * @since 2023/6/7
 */
@Component
public class MyFactoryBean<T> implements FactoryBean<T> {
    
    private Class<T> mapperInterFace;

    public Class<T> getMapperInterFace() {
        return mapperInterFace;
    }

    public void setMapperInterFace(Class<T> mapperInterFace) {
        this.mapperInterFace = mapperInterFace;
    }

    @Override
    public T getObject() throws Exception {
        
        return (T)Proxy.newProxyInstance(mapperInterFace.getClassLoader(), new Class[]{mapperInterFace}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("执行数据库查询");
                return null;
            }
        });
    }

    @Override
    public Class<T> getObjectType() {
        return mapperInterFace;
    }
}
