package com.tech.event.c;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author lw
 * @since 2023-05-16
 */
@Slf4j
//@Lazy
@Component
public class ContextRfreshedListener /*implements ApplicationListener<ContextRefreshedEvent> */{
//    @Override
    @EventListener(ContextRefreshedEvent.class)
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info("in listener");
    }
}
