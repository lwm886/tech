package com.tech.unsafe;

import lombok.extern.slf4j.Slf4j;
import sun.misc.Unsafe;

import java.util.concurrent.TimeUnit;

/**
 * @author lw
 * @since 2023-04-07
 */
@Slf4j
public class ThreadParkRunner {
    static Unsafe unsafe=UnsafeInstance.reflectGetUnsafe();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            log.info("is running");
            //线程 true实现ms定时，false实现ns
            unsafe.park(false, 0L);
            log.info("is over");
        });
        t1.start();
        TimeUnit.SECONDS.sleep(10);
        unsafe.unpark(t1);
    }
}
