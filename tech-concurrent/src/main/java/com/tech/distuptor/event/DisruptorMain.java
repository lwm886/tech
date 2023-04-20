package com.tech.distuptor.event;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author lw
 * @since 2023-04-17
 */
public class DisruptorMain {
    public static void main(String[] args) throws InterruptedException {
        //用于给消费者提供线程，处理事件
        ExecutorService exec = Executors.newCachedThreadPool();
        //创建事件工厂
        LongEventFactory longEventFactory = new LongEventFactory();
        //定义环形数组大小
        int ringBufferSize=1024*1024;
        //创建Disruptor
        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(longEventFactory,ringBufferSize,exec, ProducerType.MULTI,new YieldingWaitStrategy());
        //注册消费者（2个消费者）
        disruptor.handleEventsWith(new LongEventHandler(1),new LongEventHandler(2));
        //启动disruptor
        disruptor.start();

        //多线程发送 **（在创建Disruptor时，应选择MULTI，多生产者模式）
        for (int k = 0; k < 1; k++) {
           new Thread(()->{
               RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
               //创建生产者
               LongEventProducer longEventProducer = new LongEventProducer(ringBuffer);
               //使用ByteBuffer分配8字节JVM内存
               ByteBuffer byteBuffer = ByteBuffer.allocate(8);
               for (int i = 0; i < 100; i++) {
                   //分配的内存一共8字节，所以只能写入1个long,并且需要从第1个字节写，其索引是0
                   byteBuffer.putLong(0,i);
                   //生产者写入数据到环形数组
                   longEventProducer.onData(byteBuffer);
                   try {
                       TimeUnit.SECONDS.sleep(1);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
           }).start();
        }

        //单线程发送 **（在创建Disruptor时，应选择SINGLE，单生产者模式）
      /*  //获取环形数组
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
        //创建生产者
        LongEventProducer longEventProducer = new LongEventProducer(ringBuffer);
        //使用ByteBuffer分配8字节JVM内存
        ByteBuffer byteBuffer = ByteBuffer.allocate(8);
        for (int i = 0; i < 100; i++) {
            //分配的内存一共8字节，所以只能写入1个long,并且需要从第1个字节写，其索引是0
            byteBuffer.putLong(0,i);
            //生产者写入数据到环形数组
            longEventProducer.onData(byteBuffer);
        }*/

        TimeUnit.SECONDS.sleep(1000000000);
        disruptor.shutdown();
        exec.shutdown();
    }
}
