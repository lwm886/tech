package com.tech.cpu;

import java.util.concurrent.TimeUnit;

/**
 * @author lw
 * @since 2023-01-09
 */
public class BT {

    private static final Object o=new Object();

    public static void main(String[] args) {
        for(int i=0;i<1000;i++){
            Tasks tasks = new Tasks("T-" + i, o);
            new Thread(tasks).start();
        }
        System.out.println("start ok ...");
    }
}

class Tasks implements Runnable{

    private Object obj;

    private String threadName;

    public Tasks(String tn,Object o) {
        obj = o;
        threadName=tn;
    }

    @Override
    public void run() {
        Thread.currentThread().setName(threadName);
        for (;;){
            synchronized (obj){
            }
            try {
                TimeUnit.MILLISECONDS.sleep(1L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
