package com.tech.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * 中断方式，唤醒park，在中断状态下后续再调用park也不会阻塞；中断状态被撤销后，再调用park会阻塞
 * @author lw
 * @since 2023-04-04
 */
@Slf4j
public class LockSupportTest {
    public static void main(String[] args) throws InterruptedException {
        Object o=new Object();
        Thread t1 = new Thread(() -> {
//            while (true){
//                log.info("t1 即将Park");
//                LockSupport.park();
//                log.info("t1 解除Park");
//            }
            while (true){
                log.info("t1 即将Park");
                LockSupport.park(o);
                log.info("t1 解除Park");
//                Thread.interrupted();
            }
        });
        t1.start();
        TimeUnit.SECONDS.sleep(3);
        t1.interrupt();
    }
}
