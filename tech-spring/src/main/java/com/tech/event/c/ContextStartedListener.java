package com.tech.event.c;

import lombok.Data;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author lw
 * @since 2023-05-16
 */
@Data
@Component
public class ContextStartedListener /*implements ApplicationListener<ContextStartedEvent> */ {
    //    @Override
    @EventListener(ContextRefreshedEvent.class)
    public void onApplicationEvent(ContextStartedEvent event) {
        System.out.println("hand ContextStartedEvent");
    }
}
