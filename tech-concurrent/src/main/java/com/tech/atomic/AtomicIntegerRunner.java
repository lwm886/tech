package com.tech.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lw
 * @since 2023-04-06
 */
public class AtomicIntegerRunner {
    private static AtomicInteger ato=new AtomicInteger(0);
    private static CountDownLatch countDownLatch=new CountDownLatch(10);

    public static void main(String[] args) throws InterruptedException {
        for(int i=0;i<10;i++){
            new Thread(()->{
                for (int j = 0; j <10000; j++) {
                    ato.getAndIncrement();
                }
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
        System.out.println("main 合并结果："+ato.get());
    }
}
