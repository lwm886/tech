package com.tech.atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @author lw
 * @since 2023-04-06
 */
public class AtomicIntegerArrayRunner {
    public static void main(String[] args) {
        int[] arr=new int[]{1,2};
        AtomicIntegerArray atomicIntegerArray=new AtomicIntegerArray(arr);
        atomicIntegerArray.getAndSet(0,3);
        System.out.println(atomicIntegerArray.get(0));
        //在构建AtomicIntegerArray时，数组的值会被拷贝一份，Atomic操作的是数组的副本
        System.out.println(arr[0]);
    }
}
