package com.tech.synchronizer;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

/**
 * @author lw
 * @since 2023-03-09
 */
public class WeightSync {
    public static void main(String[] args) throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        Object a = new Object();

        Thread t1 = new Thread(()->{
            synchronized (a){
                System.out.println("t1 locking");
                System.out.println(ClassLayout.parseInstance(a).toPrintable());

                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t2 = new Thread(()->{
            synchronized (a){
                System.out.println("t2 locking");
                System.out.println(ClassLayout.parseInstance(a).toPrintable());

                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();

    }
}
