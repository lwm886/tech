package com.tech.lru;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LinkedHashMap基于HASHMAP+双向链表实现有序的Map，双向链表维护了Entry的操作顺序（写入顺序或读写顺序），最早操作的在链表的第一位，
 * 最后操作的在链表的最后一位，可以实现LRU缓存
 * @author lw
 * @since 2023/6/1
 */
public class LinkedHashMapRunner {
    public static void main(String[] args) {
        
        //构造LinkedHashMap 初始容量、加载因子、排序模式（默认false：插入排序，true：读写排序）
        //如果在构造器没有设置排序模式，那么默认就是写入排序
        //如果是写入排序，那么最早写入的是在链表的第一个元素位置，最后写入的排在链表最后一个元素位置，淘汰时淘汰链表第一个元素位置
        //如果是读写排序，那么最早读写的排在第一个元素，最后读写的排在链表最后一个位置，淘汰时淘汰链表第一个元素位置
        Map<String,Integer> map=new LinkedHashMap<String,Integer>(3,0.75f,true){
            
            //写入时回调，返回true会移除链表中的第一个元素及这个元素对应的Map中的entry
           public boolean removeEldestEntry(Map.Entry<String,Integer> eldest) {
               return size()>3;
            }
        };
        map.put("a",1);
        map.put("b",2);
        map.get("a"); //由于排序模式设置的true，也就是读写模式，get时会把key移动到链表的尾部，不会淘汰a
        map.put("c",3);
        map.put("d",4);
        System.out.println(map); //返回 a c d
    }
}
