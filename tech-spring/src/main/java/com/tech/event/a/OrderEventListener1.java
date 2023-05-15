package com.tech.event.a;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 监听器
 * @author lw
 * @since 2023-05-15
 */
@Slf4j
@Component
public class OrderEventListener1 {

    @EventListener(OrderEvent.class)
    public void onApplicationEvent(OrderEvent event) {
        if(event.getName().equals("减库存")){
            log.info("listener by annotation {} 减库存...",event.getSource());
        }
    }
}
