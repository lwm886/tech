package com.tech.event.c;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

/**
 * @author lw
 * @since 2023-05-16
 */
@ComponentScan
@Configuration
public class CarConfig {

    /**
     * 默认的事件广播器为同步调用
     * 修改为异步调用
     * @return
     */
//    @Bean("applicationEventMulticaster")
//    public ApplicationEventMulticaster getApplicationEventMulticaster(){
//        ApplicationEventMulticaster eventMulticaster=new SimpleApplicationEventMulticaster();
//        ((SimpleApplicationEventMulticaster) eventMulticaster).setTaskExecutor(new SimpleAsyncTaskExecutor());
//        return eventMulticaster;
//    }
}
