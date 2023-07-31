package com.tech.ot;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author lw
 * @since 2023/7/31
 */
public class ThreadPool {
    public static void main(String[] args) {
        ThreadPoolExecutor exec = new ThreadPoolExecutor(1, 2, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1));
        for(int i=0;i<5;i++){
            int finalI = i;
            exec.execute(()->{
                System.out.println(finalI +"任务，线程："+Thread.currentThread().getId()+"开始运行");
                try {
                    TimeUnit.HOURS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
