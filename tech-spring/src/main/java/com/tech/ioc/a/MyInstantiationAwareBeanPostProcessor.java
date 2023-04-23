package com.tech.ioc.a;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;

/**
 * @author lw
 * @since 2023-04-22
 */
//@Component
public class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if(beanClass==Car.class){
            Car car = new Car();
            car.setName("这是Bean后置处理器返回的car 实例化前执行");
            System.out.println("后置处理器返回的car 实例化前执行");
            return car;
        }
        return null;
    }
}
