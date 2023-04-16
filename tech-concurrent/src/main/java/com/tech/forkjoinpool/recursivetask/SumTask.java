package com.tech.forkjoinpool.recursivetask;

import java.util.concurrent.RecursiveTask;

/**
 * 大数组求和
 * ForkJoinPool处理的任务基本上都能使用递归处理，但递归的缺陷是：
 * 只会用单线程处理
 * 递归次数过多时会导致栈溢出
 * ForkJoinPool解决了这个问题，使用多线程并发处理，充分利用计算资源来提高效率，同时避免栈溢出发生。
 * 计算量不大的场景，没必要使用ForkJoinPool，
 * 最佳应用场景：多核、多内存、可以分隔再合并的CPU密集型任务 ，如超大规模数组求和、排序等
 *
 * @author lw
 * @since 2023-04-15
 */
public class SumTask extends RecursiveTask<Long> {

    static final int SEQUENTIAL_THRESHOLD = 100;

    int low;
    int high;
    int[] arr;

    public SumTask(int low, int high, int[] arr) {
        this.low = low;
        this.high = high;
        this.arr = arr;
    }

    /**
     * fork 将任务放入队列并安排异步执行，一个任务应只调用一次fork，除非已执行完毕并重新初始化
     * join 等待计算完成并返回结果
     * isCompletedAbnormally 判断任务计算是否发生异常
     *
     * @return
     */
    @Override
    protected Long compute() {
        if (high - low < SEQUENTIAL_THRESHOLD) {
            long sum = 0;
            for (int i = low; i < high; i++) {
                sum += arr[i];
            }
            return sum;
        } else {
            int mid = (low + high) / 2;
            SumTask left = new SumTask(low, mid, arr);
            SumTask right = new SumTask(mid, high, arr);
            left.fork();
            right.fork();
            Long leftRes = left.join();
            Long rightRes = right.join();
            return leftRes + rightRes;
        }

    }
}
