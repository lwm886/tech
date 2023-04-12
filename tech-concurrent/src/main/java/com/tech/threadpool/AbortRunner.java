package com.tech.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * 如果不考虑JVM内存，可以使用无界队列，如果考虑JVM内存，可以换成有界队列，重新拒绝处理策略，如果不想丢失任务，可以将拒绝的任务持久化，如写入到Redis或其它中间件，通过一个线程执行检测重写任务：等待线程池不忙的时候，比如检测任务队列大部分都空着如50%，然后再提交任务到线程池执行。
 * @author lw
 * @since 2023-04-12
 */
@Slf4j
public class AbortRunner {
    public static void main(String[] args) {
        BlockingQueue<Runnable> redisList = new LinkedBlockingQueue<>();

        ThreadPoolExecutor exec = new ThreadPoolExecutor(1, 5, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1), new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                log.info("执行拒绝策略 write runnable={} to redis", r);
                try {
                    redisList.put(r);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            exec.execute(() -> log.info("执行任务 {}", finalI));
        }

        new Thread(() -> {
            while (true) {
                try {
                    Runnable r=redisList.take();
                   if( exec.getQueue().size()<3){
                       log.info("重新提交任务{}到线程池",r);
                       exec.execute(r);
                   }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        }).start();
    }
}
