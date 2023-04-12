package com.tech.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 提交任务时：
 * 如果核心线程池不满就会创建核心线程运行任务，即便有核心线程在阻塞队列上使用take阻塞等待获取任务，也会创建核心线程;
 * 如果核心线程已满，则任务被添加到任务队列中，由空闲的线程取出执行
 * 如何阻塞队列已满，则会创建非线程运行任务，线程运行完，在阻塞队列上使用延时的poll方法阻塞等待获取任务，如果提交任务时队列不满会继续往队列放，如果队列是满的则表示没有线程在阻塞队列上等待，还会创建非核心线程运行任务，直到线程规模达到最大线程数，提交任务执行拒绝处理策略
 *
 * @author lw
 * @since 2023-04-12
 */
public class ThreadPoolExecutorRunner {
    public static void main(String[] args) {
        ThreadPoolExecutor exec=new ThreadPoolExecutor(3,100,60, TimeUnit.HOURS,new ArrayBlockingQueue<>(1),new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0; i < 100; i++) {
            int finalI = i;
            exec.execute(()->{
                System.out.println(Thread.currentThread().getName()+" start, activeSize="+exec.getQueue().size());
                try {
                    if(finalI >10){
                        TimeUnit.SECONDS.sleep(1);
                    }else{
                        TimeUnit.SECONDS.sleep(10000000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            try {
                    TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
