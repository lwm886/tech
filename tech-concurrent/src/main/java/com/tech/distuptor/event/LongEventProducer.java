package com.tech.distuptor.event;

import com.lmax.disruptor.RingBuffer;
import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;

/**
 * 事件生产者
 *
 * @author lw
 * @since 2023-04-19
 */
@Slf4j
public class LongEventProducer {
    //环形数组
    public final RingBuffer<LongEvent> ringBuffer;

    public LongEventProducer(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void onData(ByteBuffer byteBuffer) {
        //环形数组下一个槽
        long sequence = ringBuffer.next();
        Long data = null;
        try {
            //获取当前位置上的事件对象（用于复用对象，覆盖对象中的数据）
            LongEvent longEvent = ringBuffer.get(sequence);
            data = byteBuffer.getLong(0);
            //覆盖value
            longEvent.setValue(data);
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            log.info("生产者准备发送数据 sequence={}",sequence);
            ringBuffer.publish(sequence);
        }


    }
}
