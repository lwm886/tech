package com.tech.forkjoinpool.recursiveaction;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

/**
 * 超大数组排序，无需返回值时继承RecursiveAction创建任务
 * @author lw
 * @since 2023-04-15
 */
@Slf4j
public class SortAction extends RecursiveAction {
    private final int threshold;
    private int[] arr;

    public SortAction(int threshold, int[] arr) {
        this.threshold = threshold;
        this.arr = arr;
    }

    @Override
    protected void compute() {
        if(arr.length<threshold){
            Arrays.sort(arr);
            log.info("sorted Array={}",Arrays.toString(arr));
            return;
        }
        int midPoint=arr.length/2;
        int[] leftArray = Arrays.copyOfRange(arr, 0, midPoint);
        int[] rightArray=Arrays.copyOfRange(arr,midPoint,arr.length);

        SortAction leftAction = new SortAction(threshold, leftArray);
        SortAction rightAction = new SortAction(threshold, rightArray);

        //fork添加新的任务到任务队列
        log.info(">>>>>>>>...fork task : left={} \n right={} \n origin={}",Arrays.toString(leftArray), Arrays.toString(rightArray));
//        log.info(">>>>>>>>...fork task : left={} \n right={} \n origin={}",Arrays.toString(leftArray), Arrays.toString(rightArray),Arrays.toString(arr));
        leftAction.fork();
        rightAction.fork();
        log.info(">>>>>>>>fork task ok : left={} \n right={} \n origin={}",Arrays.toString(leftArray), Arrays.toString(rightArray));
//        log.info(">>>>>>>>fork task ok : left={} \n right={} \n origin={}",Arrays.toString(leftArray), Arrays.toString(rightArray),Arrays.toString(arr));

        //获取任务结果，如果无法获取（依赖别的任务），窃取别的任务执行
        leftAction.join();
        rightAction.join();

        arr = ArrayUtils.merge(leftAction.getSortedArray(), rightAction.getSortedArray());
        log.info("-----------------merge array : left={} \n right={} \n rs={}",Arrays.toString(leftArray), Arrays.toString(rightArray));
//        log.info("-----------------merge array : left={} \n right={} \n rs={}",Arrays.toString(leftArray), Arrays.toString(rightArray),Arrays.toString(arr));
    }

    public int[] getSortedArray(){
        return arr;
    }
}
