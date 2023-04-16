package com.tech.forkjoinpool.recursiveaction;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

/**
 * 无返回值RecursiveAction任务测试
 * @author lw
 * @since 2023-04-15
 */
@Slf4j
public class Main {
    public static void main(String[] args) {
        System.out.println("1");
        int c = Runtime.getRuntime().availableProcessors();
        ForkJoinPool forkJoinPool = new ForkJoinPool(5);
        int[] array = getArray();
        SortAction sortAction = new SortAction(5, array);
        forkJoinPool.invoke(sortAction);
    }

    public static int[] getArray(){
//        return new int[]{10000,1,3,5,7,9,11,13,15,17,2,4,6,8,10,100,1000,555,5555,555555};
        int[] arr=new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i]=new Random().nextInt(100);
        }
        return arr;
    }
}
