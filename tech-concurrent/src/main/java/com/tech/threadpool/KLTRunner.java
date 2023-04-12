package com.tech.threadpool;

import java.util.concurrent.TimeUnit;

/**
 * @author lw
 * @since 2023-04-12
 */
public class KLTRunner {
    public static void main(String[] args) {
        for (int i = 0; i < 300; i++) {
            new Thread(()->{
                try {
                    TimeUnit.SECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
