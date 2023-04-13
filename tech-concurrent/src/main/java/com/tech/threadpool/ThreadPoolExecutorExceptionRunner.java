package com.tech.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @author lw
 * @since 2023-04-13
 */
@Slf4j
public class ThreadPoolExecutorExceptionRunner {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ThreadPoolExecutor exec = new ThreadPoolExecutor(1, 10, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        m1(exec);
//        m2(exec);

    }

    private static void m1(ThreadPoolExecutor exec) throws InterruptedException {
        exec.execute(()->{
            log.info("invoke ...");
            throw new RuntimeException();
        });
        TimeUnit.SECONDS.sleep(5);
        exec.execute(()->{
            log.info("invoke ...");
            throw new RuntimeException();
        });
    }

    public static void m2(ThreadPoolExecutor exec) throws InterruptedException, ExecutionException {
        Future<String> future = exec.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.info("warn throw exception");
                throw new RuntimeException();
//                return "OK";
            }
        });
        try {
            System.out.println(future.get());
        } catch (Throwable e) {
            log.error("future.get error", e);
        }

        TimeUnit.SECONDS.sleep(10);

        Future<String> future1 = exec.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.info("warn throw exception 1");
                throw new RuntimeException();
//                return "OK";
            }
        });
        System.out.println(future.get());
    }
}
