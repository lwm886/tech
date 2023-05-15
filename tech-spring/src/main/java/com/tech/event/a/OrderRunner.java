package com.tech.event.a;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author lw
 * @since 2023-05-15
 */
@Slf4j
public class OrderRunner {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(OrderConfig.class);
        log.info("create order...");
        applicationContext.publishEvent(new OrderEvent(new Order("1"),"减库存"));
        log.info("write log...");
    }
}
