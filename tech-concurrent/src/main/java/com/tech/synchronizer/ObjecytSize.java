package com.tech.synchronizer;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

/**
 * @author lw
 * @since 2023-03-08
 */
public class ObjecytSize {
    public static void main(String[] args) throws InterruptedException {
        TimeUnit.SECONDS.sleep(5); //偏向锁延迟启动，等待5s,JVM启动偏向锁
        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        synchronized (o){
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
    }
}
