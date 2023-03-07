package com.tech.synchronizer;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author lw
 * @since 2023-02-28
 */
public class CountDownMultiThread {
    private static int k = 0;

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(1);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int c = 0; c < 1000; c++) {
                    synchronized (CountDownMultiThread.class){
                        k++;
                    }
                }
            }).start();
        }
        latch.countDown();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(k);
    }
}
