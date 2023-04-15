package com.tech.forkjoinpool.multithread;

/**
 * @author lw
 * @since 2023-04-14
 */
public class SumUtils {
    public long sumRange(int[] arr, int lo, int hi) {
        long result = 0;
        for (int i = lo; i < hi; i++) {
            result += arr[i];
        }
        return result;
    }
}
