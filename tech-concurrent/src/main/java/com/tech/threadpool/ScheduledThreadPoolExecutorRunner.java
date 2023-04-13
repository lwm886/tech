package com.tech.threadpool;

import lombok.extern.slf4j.Slf4j;

import javax.management.relation.RoleUnresolved;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;

/**
 * @author lw
 * @since 2023-04-13
 */
@Slf4j
public class ScheduledThreadPoolExecutorRunner {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ScheduledExecutorService exec = new ScheduledThreadPoolExecutor(1);
        //延迟一定时间执行，只执行一次
//         m1(exec);

        //延迟一定时间执行，只执行一次，返回返回值(实际上ScheduledThreadPoolExecutor对待runnable任务会将其转为runnable带返回值的任务，因此如果任务执行过程出现异常，线程池中的线程不会抛出异常，如果用户线程没有通过Future的get获取结果，那么用户线程也不会感知到异常,所以在调度无返回值任务时，要在任务中手动捕获异常处理)
//        ScheduledFuture<Integer> scheduledFuture = m2(exec);
//        System.out.println(scheduledFuture.get());

        //固定频率周期调度，如果任务耗时超过调度周期，会立即调度下次任务
//        m3(exec);

        //固定延迟周期调度，任务执行完后，延迟指定时间进行下次调度
//        m4(exec);


        m5(exec);

    }

    private static void m5(ScheduledExecutorService exec) throws InterruptedException {
//        ScheduledThreadPoolExecutor线程抛出异常，可以继续提交任务
        exec.scheduleAtFixedRate(()->{
            log.info("test error invoke");
            //抛出异常不再调度这个任务
//            throw new RuntimeException();
        },1,1,TimeUnit.SECONDS);
//        TimeUnit.SECONDS.sleep(3);
        exec.scheduleAtFixedRate(()->{
            log.info("test error invoke 1");
//            throw new RuntimeException();
        },1,1,TimeUnit.SECONDS);
        TimeUnit.SECONDS.sleep(3);
        exec.scheduleAtFixedRate(()->{
            log.info("test error invoke A");
            throw new RuntimeException();
        },1,1,TimeUnit.SECONDS);

        //time采用单线程执行任务，任务异常，线程终结，timer不能用了
//        Timer timer = new Timer();
//        timer.scheduleAtFixedRate(new TimerTask() {
//            @Override
//            public void run() {
//                log.info("invoke ");
//                throw new RuntimeException();
//            }
//        },1000,1000);
//        TimeUnit.SECONDS.sleep(5);
//        timer.scheduleAtFixedRate(new TimerTask() {
//            @Override
//            public void run() {
//                log.info("invoke 1");
//                throw new RuntimeException();
//            }
//        },1000,1000);
    }

    private static void m4(ScheduledExecutorService exec) {
        exec.scheduleWithFixedDelay(()->{
            log.info("send heart beat");

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },1,1,TimeUnit.SECONDS);
    }

    private static void m3(ScheduledExecutorService exec) {
        exec.scheduleAtFixedRate(()->{
            log.info("send heart beat");
            //内部为callable调用，抛出异常，线程池线程会捕获异常不在调度这个任务
//            throw new RuntimeException("error");

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },1,1,TimeUnit.SECONDS);

    }

    private static ScheduledFuture<Integer> m2(ScheduledExecutorService exec) {
        ScheduledFuture<Integer> future = exec.schedule(() -> {
            log.info("延迟3s，返回返回值");
            //线程池在执行callable有返回值的任务时，如果抛出异常，异常会被线程池捕获并不会将异常抛给线程池，而是在用户通过future调用get获取结果时将异常抛给用户线程
            throw new RuntimeException("error");
//            return 1;
        }, 3, TimeUnit.SECONDS);
        return future;
    }

    private static void m1(ScheduledExecutorService exec) {
        exec.schedule(() -> {
            log.info("延迟3s执行");
        }, 3, TimeUnit.SECONDS);

    }
}
