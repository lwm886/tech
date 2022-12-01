package com.tech.jvm.mat1;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lw
 * @since 2022/8/4
 */
public class ListRatioDemo {
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10000; i++) {
            Thread thread=new Thread(new Runnable() {
                @Override
                public void run() {
                    HolderContainer holderContainer1 = new HolderContainer();
                    try {
                        Thread.sleep(1000 * 1000 * 60);
                    } catch (InterruptedException e) {
                        System.exit(1);
                    }
                }
            });
            thread.setName("inner-thread-"+i);
            thread.start();
        }
    }
}

class HolderContainer {
    ListHolder listHolder1 = new ListHolder().init();
    ListHolder listHolder2 = new ListHolder().init();
}

class ListHolder {
    static final int LIST_SIZE = 100 * 1000;
    List<String> list1 = new ArrayList(LIST_SIZE); //5%填充
    List<String> list2 = new ArrayList(LIST_SIZE); //5%填充
    List<String> list3 = new ArrayList(LIST_SIZE); //15%填充
    List<String> list4 = new ArrayList(LIST_SIZE); //30%填充

    public ListHolder init() {
        for (int i = 0; i < LIST_SIZE; i++) {
            if (i < 0.001 * LIST_SIZE) {
                list1.add(" " + i);
                list2.add(" " + i);
            }
            if (i < 0.005 * LIST_SIZE) {
                list3.add(" " + i);
            }
            if (i < 0.3 * LIST_SIZE) {
                list4.add(" " + i);
            }
        }
        return this;
    }
}
