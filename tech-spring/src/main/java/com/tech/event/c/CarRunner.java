package com.tech.event.c;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author lw
 * @since 2023-05-16
 */
@Slf4j
public class CarRunner {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(CarConfig.class);
//        Car bean = applicationContext.getBean(Car.class);
//        log.info(bean.hashCode()+"");
    }
}
