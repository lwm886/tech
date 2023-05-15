package com.tech.event.b;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author lw
 * @since 2023-05-15
 */
@Slf4j
@Component
public class ContextClosedEventListener {

    @EventListener(ContextClosedEvent.class)
    public void onApplicationEvent(ContextClosedEvent event){
        log.info("容器Closed");
    }
}
