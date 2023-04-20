package com.tech.distuptor.event;

import com.lmax.disruptor.EventFactory;

/**
 * 创建事件工厂，让Disruptor为我们创建事件
 * @author lw
 * @since 2023-04-17
 */
public class LongEventFactory implements EventFactory<LongEvent> {

    @Override
    public LongEvent newInstance() {
        return new LongEvent();
    }
}
