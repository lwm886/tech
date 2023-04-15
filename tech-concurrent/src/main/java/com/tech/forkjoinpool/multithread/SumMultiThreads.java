package com.tech.forkjoinpool.multithread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 使用多线程对一个大数组求和，平均分配计算量给每个线程
 *
 * @author lw
 * @since 2023-04-14
 */
public class SumMultiThreads {
    public static final int NUM = 10000;

    public static long sum(int[] arr, ExecutorService executorService) throws Exception {
        long result = 0;
        int numThreads = arr.length / NUM > 0 ? arr.length / NUM : 1;
        SumTask[] sumTasks = new SumTask[numThreads];
        Future<Long>[] futures = new Future[numThreads];
        for (int i = 0; i < numThreads; i++) {
            sumTasks[i]=new SumTask(i * NUM, (i+1) * NUM, arr);
            futures[i]=executorService.submit(sumTasks[i]);
        }
        for(Future<Long> future:futures){
            result+=future.get();
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        int[] arr=new int[1000000];
        for (int i = 0; i <1000000; i++) {
            arr[i]=1;
        }
        int numThreads = arr.length / NUM > 0 ? arr.length / NUM : 1;
        ExecutorService exec = Executors.newFixedThreadPool(numThreads);
        long sum = sum(arr, exec);
        System.out.println(sum);
        exec.shutdown();
    }
}
