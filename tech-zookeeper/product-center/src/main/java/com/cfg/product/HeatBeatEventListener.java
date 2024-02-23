package com.cfg.product;

import org.springframework.cloud.client.discovery.event.HeartbeatEvent;
import org.springframework.context.ApplicationListener;

/**
 * @author lw
 * @since 2024/2/23
 */
public class HeatBeatEventListener implements ApplicationListener<HeartbeatEvent> {
    @Override
    public void onApplicationEvent(HeartbeatEvent heatBeatEvent) {
        
    }
}
