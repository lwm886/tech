package com.tech.ioc.h;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author lw
 * @since 2023-05-06
 */
//@Component
@Slf4j
public class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {

    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if(beanClass==Car.class){
            Car car=new Car();
            car.setName("InstantiationAwareBeanPostProcessor");
            log.info("返回InstantiationAwareBeanPostProcessor 创建的bean");
            return car;
        }
        return null;
    }
}
