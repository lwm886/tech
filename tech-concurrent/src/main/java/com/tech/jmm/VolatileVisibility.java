package com.tech.jmm;

/**
 * Volatile 不能保证原子性
 *
 * @author lw
 * @since 2022-12-01
 */
public class VolatileVisibility {

    public static volatile int i;

    public static void increase() {
        i++;
    }

    public static void main(String[] args) {

        Thread threadA = new Thread(() -> {
            for(int i=0;i<5000;i++){
                increase();
            }
        }, "threadA");
        Thread threadB = new Thread(() -> {
            for(int i=0;i<5000;i++){
                increase();
            }
        }, "threadB");

        threadB.start();
        threadA.start();
        try {
            threadA.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            threadB.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(i);
    }
}
