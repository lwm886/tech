package com.outofmemory;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

/**
 * 一个线程在出现OOM后，这个线程会立刻被终结，线程栈空间也会被释放，其他线程可以继续执行，仅由这个线程持有的对象占用的堆空间会
 * 在下次GC时释放掉
 * -Xms300M -Xmx300M
 * @author lw
 * @since 2022/12/5
 */
public class OutMemoryTest {

    private static final int SIZE = 1024 * 1024*10;

    public static void main(String[] args) {
        new Thread(()->{
            b();
        },"T-B").start();
        new Thread(()->{
            a();
        },"T-A").start();
    }

    public static void a() {
        List<byte[]> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            byte[] bytes = new byte[SIZE];
            list.add(bytes);
            System.out.println(Thread.currentThread().getName()+" "+list.size());
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void b() {
        for(int i=0;i<100;i++){
            System.out.println(Thread.currentThread().getName()+" PING ...");
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
