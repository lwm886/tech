package com.tech.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 信号量 限制访问资源的数量，常用于服务限流，如Hystrix就有用这种限流方式。
 *
 * @author lw
 * @since 2023/4/5
 */
@Slf4j
public class SemaphoreRunner {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2);
        for(int i=0;i<5;i++){
            new Thread(new Task("t-"+i,semaphore)).start();
        }
    }

    static class Task implements Runnable {
        private String threadName;
        private Semaphore semaphore;

        public Task(String threadName, Semaphore semaphore) {
            this.threadName=threadName;
            this.semaphore=semaphore;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();
                log.info("获取到资源 执行业务");
                TimeUnit.SECONDS.sleep(5);
                semaphore.release();
                log.info("释放资源");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
