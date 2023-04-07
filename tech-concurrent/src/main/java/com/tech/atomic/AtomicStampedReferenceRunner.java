package com.tech.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.locks.LockSupport;

/**
 * @author lw
 * @since 2023-04-07
 */
@Slf4j
public class AtomicStampedReferenceRunner {
    private static AtomicStampedReference<Integer> ato=new AtomicStampedReference<>(1,0);

    public static void main(String[] args) {
        Thread main = new Thread(()->{
            int stamp = ato.getStamp();
            Integer val = ato.getReference();
            System.out.println("操作线程"+Thread.currentThread()+",stamp="+stamp+",初始值val="+val);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                log.error("异常",e);
            }
            boolean b = ato.compareAndSet(val, 2, stamp, stamp + 1);
            System.out.println("操作线程"+Thread.currentThread()+",stamp="+stamp+",CAS操作结果:"+b);
        },"主操作线程");

        Thread other = new Thread(() -> {
            int stamp = ato.getStamp();
            ato.compareAndSet(1,2,stamp,stamp+1);
            System.out.println("操作线程"+Thread.currentThread()+",stamp="+stamp+",incr 值 val="+ato.getReference());
            stamp = ato.getStamp();
            ato.compareAndSet(2,1,stamp,stamp+1);
            System.out.println("操作线程"+Thread.currentThread()+",stamp="+stamp+",decr 值 val="+ato.getReference());
        }, "干扰操作线程");

        main.start();
        LockSupport.parkNanos(1000000);
        other.start();
    }
}
