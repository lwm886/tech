package com.tech.jvm.arthas;

import java.util.HashSet;

/**
 * @author lw
 * @since 2022/7/22
 */
public class Arthas {

    private static HashSet hashSet = new HashSet();

    public static void main(String[] args) {
        //模拟CPU过高
        cpuHigh();
        //模拟线程死锁
        deadThread();
        //不断的向HashSet集合增加数据
        addHashSetThread();
        //test watch
        for(int i=0;i<99999;i++){
            new Arthas().testWatch(i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private int testWatch(int i) {
        return i*10;
    }

    /**
     * 不断的向HashSet集合增加数据
     */
    private static void addHashSetThread() {
        new Thread(
                () -> {
                    int count = 0;
                    while (true) {
                        try {
                            hashSet.add("count" + count);
                            Thread.sleep(1000);
                            count++;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        ).start();
    }

    /**
     * 死锁
     */
    private static void deadThread() {
        //创建资源
        Object resourceA = new Object();
        Object resourceB = new Object();
        //创建线程
        Thread threadA = new Thread(()->{
            synchronized (resourceA){
                System.out.println(Thread.currentThread()+"get resourceA");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread()+"waiting get resourceB");
                synchronized (resourceB){
                    System.out.println(Thread.currentThread()+"get resourceB");
                }
            }
        });
        Thread threadB = new Thread(()->{
            synchronized (resourceB){
                System.out.println(Thread.currentThread()+"get resourceB");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread()+"waiting get resourceA");
                synchronized (resourceA){
                    System.out.println(Thread.currentThread()+"get resourceA");
                }
            }
        });
        threadA.start();
        threadB.start();
    }

    /**
     * CPU过高
     */
    private static void cpuHigh() {
        new Thread(() -> {
            while (true) {
            }
        }
        ).start();
    }


}
