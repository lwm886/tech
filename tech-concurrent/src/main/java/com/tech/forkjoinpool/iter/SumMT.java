package com.tech.forkjoinpool.iter;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * 如果使用 FixedThreadPool需要确保线程数大于递归拆分的任务数，如果小于那么所有的线程在等待FutureTask结果时被park，那么会出现没有可用的线程去执行剩余的任务现象，无法完成计算
 * 使用newCachedThreadPool 没有问题
 * @author lw
 * @since 2023-04-14
 */
@Slf4j
public class SumMT {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int[] arr=new int[40];
        for (int i = 0; i <40; i++) {
            arr[i]=1;
        }
        long sum = sum(arr);
        log.info("result={}",sum);
    }

    public static long sum(int[] arr) throws ExecutionException, InterruptedException {
        ExecutorService exec = Executors.newFixedThreadPool(10);
//        ExecutorService exec = Executors.newCachedThreadPool();
        Task task = new Task(0, arr.length, arr, exec);
        long rs = exec.submit(task).get();
        return rs;
    }

    public static class Task implements Callable<Long> {
        public static final int SEQUENTIAL_CUTOFF = 10;
        int lo;
        int hi;
        int[] arr;
        ExecutorService exec;

        public Task(int lo, int hi, int[] arr, ExecutorService exec) {
            this.lo = lo;
            this.hi = hi;
            this.arr = arr;
            this.exec = exec;
        }

        @Override
        public Long call() throws Exception {
            log.info("ready cal lo={},hi={}", lo, hi);
            long result = 0;
            if (hi - lo <= SEQUENTIAL_CUTOFF) {
                for (int i = lo; i < hi; i++) {
                    result += arr[i];
                }
            } else {
                Task leftTask=new Task(lo,(lo+hi)/2,arr,exec);
                Task rightTask=new Task((lo+hi)/2,hi,arr,exec);
                Future<Long> lf = exec.submit(leftTask);
                Future<Long> rf = exec.submit(rightTask);
                log.info("ready split cal wait get llo={},lhi={},rlo={},rhi={}", lo, (lo+hi)/2, (lo+hi)/2,hi);
                result=lf.get()+rf.get();
                log.info("ready split cal got llo={},lhi={},rlo={},rhi={}", lo, (lo+hi)/2, (lo+hi)/2,hi);

            }

            return result;
        }
    }
}
