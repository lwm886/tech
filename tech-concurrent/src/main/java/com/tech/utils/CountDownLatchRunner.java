package com.tech.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * CountDownLatch也可以实现多个线程等1个线程
 * @author lw
 * @since 2023/4/5
 */
@Slf4j
public class CountDownLatchRunner {
    private static int k=0;
    private static final Object o=new Object();
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch=new CountDownLatch(1);
        for(int i=0;i<10;i++){
            new Thread(()->{
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o){
                    k++;
                    log.info(k+"");
                }
            }).start();
        }
        TimeUnit.SECONDS.sleep(1);
        System.out.println("main 执行");
        countDownLatch.countDown();
    }
}
