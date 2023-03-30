package com.tech.locksupport;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author lw
 * @since 2023-03-30
 */
@Slf4j
public class LockSupportTest {
    public static void main(String[] args) throws InterruptedException {
        //park 阻塞，unPark 唤醒
//        m1();
        //先unPark 后 park 依旧可以唤醒
//        m2();
        //先两次 park，再两次unPark 可以唤醒
//        m3();
        //先两次 unPark，再两次park 不能唤醒
//        m4();

    }

    private static void m4() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            log.info("t1 进入方法");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("t1 即将阻塞1");
            LockSupport.park();
            log.info("t1 即将阻塞2");
            LockSupport.park();
            log.info("t1 被唤醒");
        }, "t1");
        t1.start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(()->{
            LockSupport.unpark(t1);
            log.info("t2 唤醒t1 1次");
            LockSupport.unpark(t1);
            log.info("t2 唤醒t1 2次");
        },"t2").start();

    }

    private static void m3() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            log.info("t1 进入方法,即将阻塞");
            LockSupport.park();
            log.info("t1 被唤醒1");
            LockSupport.park();
            log.info("t1 被唤醒2");
        }, "t1");
        t1.start();

        TimeUnit.SECONDS.sleep(3);

        new Thread(()->{
            log.info("t2 唤醒t1 1次");
            LockSupport.unpark(t1);

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            log.info("t2 唤醒t1 2次");
            LockSupport.unpark(t1);

        },"t2").start();

    }

    private static void m1() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            log.info("t1 进入方法,即将阻塞");
            LockSupport.park();
            log.info("t1 被唤醒");
        }, "t1");
        t1.start();

        TimeUnit.SECONDS.sleep(3);

        new Thread(()->{
            log.info("t2 唤醒t1");
            LockSupport.unpark(t1);
        },"t2").start();

    }

    private static void m2() throws InterruptedException {

        Thread t1 = new Thread(() -> {
            log.info("t1 进入方法");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("t1 即将阻塞");
            LockSupport.park();
            log.info("t1 被唤醒");
        }, "t1");
        t1.start();
        TimeUnit.SECONDS.sleep(1);

        new Thread(()->{
            log.info("t2 唤醒t1");
            LockSupport.unpark(t1);
        },"t2").start();


    }
}
