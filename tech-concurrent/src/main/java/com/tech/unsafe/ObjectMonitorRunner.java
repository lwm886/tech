package com.tech.unsafe;

import sun.misc.Unsafe;

import java.util.concurrent.TimeUnit;

/**
 * @author lw
 * @since 2023-04-07
 */
public class ObjectMonitorRunner {
    private static Object object=new Object();
    private static Unsafe unsafe=UnsafeInstance.reflectGetUnsafe();

    public static void main(String[] args) throws InterruptedException {
       new Thread(()->{
           m1();
           try {
               TimeUnit.SECONDS.sleep(5);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           m2();
       }).start();
        TimeUnit.SECONDS.sleep(1);
       synchronized (object){
           System.out.println(Thread.currentThread().getName()+"获取到了锁");
       }

    }

    private static void m1(){
        unsafe.monitorEnter(object);
        System.out.println(Thread.currentThread()+"获取锁");

    }
    private static void m2(){
        System.out.println(Thread.currentThread()+"释放锁");
        unsafe.monitorExit(object);
    }
}
