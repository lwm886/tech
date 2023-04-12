package com.tech.collection;

import java.util.Comparator;
import java.util.TreeMap;

/**
 * @author lw
 * @since 2023-04-12
 */
public class TreeMapRunner {
    public static void main(String[] args) {
        //默认升序
        TreeMap<Integer,String> map=new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                //降序
//                return o2-o1;
                //升序
                return o1-o2;
            }
        });
        map.put(7,"b");
        map.put(5,"a");
        map.put(16,"c");
        map.put(161,"d");
        map.forEach((k,v)-> System.out.println("k="+k+",v="+v));
        //升序时大于等于11最小Entry，降序时小于等于11的最大Entry
        System.out.println(map.ceilingEntry(11));
        //升序时小于等于11最大Entry，降序时大于等于11最小Entry
        System.out.println(map.floorEntry(11));
        //第一个Entry
        System.out.println(map.pollFirstEntry());
        //最后一个Entry
        System.out.println(map.pollLastEntry());
        System.out.println("----------");
        //返回key中间的节点数据，开区间不含两侧节点，如果范围key不存在，则其包含的第一个节点为两侧节点，同样不包含本例中1-166和5-161返回的数据相同
        System.out.println(map.subMap(5,161));
    }
}
