package com.tech.synchronizer;

import java.util.concurrent.TimeUnit;

/**
 * 消除：
 * JVM在JIT即时编译时，通过扫描上下文，可能会消除掉不会产生竞争的锁，减少加减锁的开销，提升程序性能，这就是锁消除。锁消除需要逃逸分析的支持，没有在线程或方法逃逸的锁对象，有可能会被锁消除优化。逃逸分析是JVM在JIT即时编译时，分析对象是否会逃离线程或方法，对于没有逃逸的对象会进行一些优化，如在栈上分配内存，减少堆内存的分配开销，标量替换，对象在栈上存储会进行标量替换，将对象替换为标量，也就是八大基本类型存储在栈上。
 *
 * 关闭逃逸分析，同时调大堆内存，打开GC日志，jmap对象创建情况
 * VM运行参数：-Xmx4G -Xms4G -XX:-DoEscapeAnalysis -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError
 *
 * 开启逃逸分析，同时调大堆内存，打开GC日志，jmap对象创建情况
 * VM运行参数：-Xmx4G -Xms4G -XX:+DoEscapeAnalysis -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError
 *
 * jmap -histo 进程ID
 * @author lw
 * @since 2023-03-29
 */
public class ObjectStackAlloc {
    public static void main(String[] args) {
        long start=System.currentTimeMillis();
        for(int i=0;i<500000;i++){
            alloc();
        }
        long end=System.currentTimeMillis();
        System.out.println("cost time "+(end-start)+" ms");

        try {
            TimeUnit.SECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static Student alloc() {
        //JIT即时编译时会进行逃逸分析，并不是所有对象都在堆上分配内存，有一部分对象可能在栈上分配内存
        Student student = new Student();
        return student;
    }
}

class Student{
    private String name;
    private int age;
}
