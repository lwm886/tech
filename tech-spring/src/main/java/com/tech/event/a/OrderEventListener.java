package com.tech.event.a;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 监听器
 * @author lw
 * @since 2023-05-15
 */
@Component
@Slf4j
public class OrderEventListener implements ApplicationListener<OrderEvent> {

    @Override
    public void onApplicationEvent(OrderEvent event) {
        if(event.getName().equals("减库存")){
            log.info("{} 减库存...",event.getSource());
        }
    }

}
