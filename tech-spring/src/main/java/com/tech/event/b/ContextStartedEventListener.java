package com.tech.event.b;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author lw
 * @since 2023-05-15
 */
@Slf4j
@Component
public class ContextStartedEventListener {

    @EventListener(ContextStartedEvent.class)
    public void onApplicationEvent(ContextStartedEvent event){
        log.info("容器started");
    }
}
