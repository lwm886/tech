package com.tech.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author lw
 * @since 2023-04-12
 */
public class CopyOnWriteArrayListRunner {
    public static void main(String[] args) {
//        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        for(String s:list){
            boolean remove = list.remove(s);
            System.out.println(remove);
        }
    }
}
