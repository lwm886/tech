package com.tech.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lw
 * @since 2023-04-04
 */
@Slf4j
public class LockInterrupt {
    public static ReentrantLock lock = new ReentrantLock(true);
    static boolean flag = false;

    public static void main(String[] args) {
        List<Thread> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                lock.lock();
                log.info("get lock ...");
                while (!flag) {
                    if (flag) {
                        break;
                    }
                }
                lock.unlock();
            },"t-"+i);
            list.add(thread);
            thread.start();
        }
        try {
            TimeUnit.SECONDS.sleep(5);
            log.info("interrupt 线程");
            list.get(3).interrupt();
        } catch (InterruptedException e) {
            log.error("error",e);
        }
    }
}
