package com.tech.collection;

import java.util.Comparator;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * ConcurrentSkipListMap是一个线程安全有序的map，底层是基于跳表组织数据的，增删改查时间复杂度为o(logn)基于自旋+cas无锁同步技术保证操作的线程安全性.
 * @author lw
 * @since 2023-04-12
 */
public class ConcurrentSkipListMapRunner {
    public static void main(String[] args) {
        ConcurrentSkipListMap<Integer, String> map = new ConcurrentSkipListMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        });
        map.put(10,"a");
        map.put(7,"b");
        map.put(11,"c");
        System.out.println(map.pollFirstEntry());
        System.out.println(map.pollLastEntry());
        ConcurrentNavigableMap map1 = map.subMap(7, 11);
        System.out.println(map1);
    }
}
