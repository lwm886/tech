package com.tech.aqs;

import lombok.extern.slf4j.Slf4j;
import sun.misc.Unsafe;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author lw
 * @since 2023-03-31
 */
@Slf4j
public class Cas {
    private volatile int state = 0;
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(5);
    private static Cas cas = new Cas();
    private static final Unsafe unsafe = UnsafeInstance.reflectGetUnsafe();
    private static final long stateOffset;

    static {
        try {
            stateOffset = unsafe.objectFieldOffset(Cas.class.getDeclaredField("state"));
        } catch (Exception e) {
            throw new Error();
        }
    }

    public final boolean compareAndSwapState(int oldValue, int newValue) {
        return unsafe.compareAndSwapInt(this, stateOffset, oldValue, newValue);
    }


    public static void main(String[] args) {
        new Thread(new Worker(),"t-1").start();
        new Thread(new Worker(),"t-2").start();
        new Thread(new Worker(),"t-3").start();
        new Thread(new Worker(),"t-4").start();
        new Thread(new Worker(),"t-5").start();
    }

    static class Worker implements Runnable {

        @Override
        public void run() {
            log.info("请求{}到达预定点，开始抢state",Thread.currentThread().getName());
            try {
                cyclicBarrier.await();
                if (cas.compareAndSwapState(0, 1)) {
                    log.info("当前请求{}抢到锁",Thread.currentThread().getName());
                } else {
                    log.info("当前请求{}抢锁失败",Thread.currentThread().getName());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

}
