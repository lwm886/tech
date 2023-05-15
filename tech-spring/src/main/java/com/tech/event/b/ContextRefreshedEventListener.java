package com.tech.event.b;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 上下文刷新事件监听器
 * 刷新IOC容器时所有的单例Bean创建完成后触发
 * @author lw
 * @since 2023-05-15
 */
@Slf4j
@Component
public class ContextRefreshedEventListener {

    @EventListener(ContextRefreshedEvent.class)
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().getParent() == null) {
            log.info("容器启动完毕");
        }
    }
}
