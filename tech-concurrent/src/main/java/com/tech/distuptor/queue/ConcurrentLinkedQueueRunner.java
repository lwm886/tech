package com.tech.distuptor.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author lw
 * @since 2023-04-18
 */
public class ConcurrentLinkedQueueRunner {
    public static void main(String[] args) {
        //有时在一些场景下，我们希望有一个有界线程安全的队列，同时局具备高性能

        //无锁线程安全队列，无界队列，在一些场景下可能导致内存溢出
        ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();
        //有锁线程安全，性能低
        ArrayBlockingQueue arrayBlockingQueue=new ArrayBlockingQueue(10);

//        Disrupor是一个支持无锁并发操作的高性能队列，log4j2使用Disruptor代替JDK的ArrayBlockingQueue
//        Disrupor
    }
}
