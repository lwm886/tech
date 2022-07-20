package com.tech.jvm.opt;

/**
 * @author lw
 * @since 2022/7/15
 * -Xms5m -Xmx5m -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=C:\tmp\jvm.dump
 */
public class Math {
    public static final int initData=666;

    public int compute(){
        int a=1;
        int b=2;
        int c=(a+b)*10;
        return c;
    }

    public static void main(String[] args) {
        new Thread(()->{
            Math math=new Math();
            while (true){
                math.compute();
            }
        }).start();
        new Thread(()->{
            Math math=new Math();
            math.m1();
        }).start();
        new Thread(()->{
           while (true){
               try {
                   Thread.sleep(1000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               System.out.println("ok");
           }
        }).start();
    }

    private void m1() {
        System.out.println("in m1");
        m2();
    }

    private void m2() {
        System.out.println("in m2");
        Math math = new Math();
        while (true){
            math.compute();
        }
    }
}
