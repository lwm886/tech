package com.tech.synchronizer;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

/**
 * @author lw
 * @since 2023-03-09
 */
public class BasicSyncLock {
    public static void main(String[] args) throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        new Thread(
                () -> {
                    synchronized (o) {
                        System.out.println(ClassLayout.parseInstance(o).toPrintable());
                    }
                }
        ).start();

        TimeUnit.SECONDS.sleep(2);
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        new Thread(
                () -> {
                    synchronized (o) {
                        System.out.println(ClassLayout.parseInstance(o).toPrintable());
                    }
                }
        ).start();
    }
}
