package com.tech.distuptor.event;


import com.lmax.disruptor.EventHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * 事件消费者
 * @author lw
 * @since 2023-04-19
 */
@Slf4j
public class LongEventHandler implements EventHandler<LongEvent> {

    private long serial = 0;

    public LongEventHandler(long serial) {
        this.serial = serial;
    }

    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
        log.info("消费者-serial={}:::::: val={},sequence={},endOfBatch={}", this.serial, event.getValue(),sequence,endOfBatch);
    }
}
