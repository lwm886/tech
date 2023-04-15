package com.tech.forkjoinpool.multithread;

import java.util.concurrent.Callable;

/**
 * @author lw
 * @since 2023-04-14
 */
public class SumTask implements Callable<Long> {
    int lo;
    int hi;
    int[] arr;

    public SumTask(int lo, int hi, int[] arr) {
        this.lo = lo;
        this.hi = hi;
        this.arr = arr;
    }

    @Override
    public Long call() throws Exception {
        long sum=0;
        for (int i = lo; i < hi; i++) {
            sum+=arr[i];
        }
        return sum;
    }
}
