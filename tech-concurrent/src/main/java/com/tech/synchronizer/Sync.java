package com.tech.synchronizer;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

/**
 * 开启偏向锁 -XX:+UseBiasedLocking
 * 关闭偏向锁 -XX:-UseBiasedLocking
 * @author lw
 * @since 2023-03-20
 */
public class Sync {
    public static void main(String[] args) throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        Object o = new Object();
        System.out.println("1>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>:");
        //匿名偏向（偏向锁延迟启动，需要等待5秒钟在系统的偏向锁启动后，再测试，匿名偏向也是一种可偏向，没有线程ID，相当于无锁状态；如果不等5s，由于偏向锁还未启动，此时的状态是无锁状态）
        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        synchronized (o) {
            System.out.println("2>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>:");
            //偏向锁 只有一个线程获取锁，进入偏向锁状态
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }

        synchronized (o) {
            System.out.println("3>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>:");
            //偏向锁 当前线程再次获取锁依旧是偏向锁
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }

        new Thread(
                () -> {
                    synchronized (o) {
                        System.out.println("4>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>:");
                        //如果同一时间只有这一个线程获取锁，则为轻量级锁，如果是多个线程则为重量级锁，如果打开下面的sleep语句这里为轻量级锁，如果关闭sleep语句则这里为重量级锁
                        System.out.println(ClassLayout.parseInstance(o).toPrintable());
                    }
                }
        ).start();

//        TimeUnit.SECONDS.sleep(1);

        synchronized (o) {
                        System.out.println("5>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>:");
            //如果同一时间只有这一个线程获取锁，则为轻量级锁，如果是多个线程则为重量级锁，如果打开上面的sleep语句这里为轻量级锁，如果关闭sleep语句则这里为重量级锁
                        System.out.println(ClassLayout.parseInstance(o).toPrintable());//轻量级锁
                    }

    }
}
