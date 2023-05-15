package com.tech.event.b;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author lw
 * @since 2023-05-15
 */
@Slf4j
@Component
public class ContextStoppedEventListener {

    @EventListener(ContextStoppedEvent.class)
    public void onApplicationEvent(ContextStoppedEvent event){
        log.info("容器Stopped");
    }
}
