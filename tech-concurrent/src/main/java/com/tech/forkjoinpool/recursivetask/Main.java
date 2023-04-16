package com.tech.forkjoinpool.recursivetask;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * @author lw
 * @since 2023-04-15
 */
public class Main {
    private static final int NCPU=Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int[] arr=getArray();
        SumTask sumTask = new SumTask(0, arr.length, arr);
        ForkJoinPool fjp = new ForkJoinPool();
        long begin=System.currentTimeMillis();
        //submit返回future，通过future.get获取结果
//        ForkJoinTask<Long> submit = fjp.submit(sumTask);
        Long invoke = fjp.invoke(sumTask);
        System.out.println(invoke+" fjp time="+(System.currentTimeMillis()-begin));
        System.out.println("窃取任务数："+fjp.getStealCount());
        long begin1=System.currentTimeMillis();
        long total = total(arr);
        System.out.println(total+" single time="+(System.currentTimeMillis()-begin1));


    }

    public static int[] getArray(){
        int[] ints = new int[100000000];
        for(int i=0;i<ints.length;i++){
            ints[i]=new Random().nextInt(100);
        }
        return ints;
    }

    public static long total(int[] arr){
        long s=0;
        for(int i=0;i<arr.length;i++){
            s+=arr[i];
        }
        return s;
    }

}
