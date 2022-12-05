package com.outofmemory;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

/**
 * 一个线程发生OOM，仅由这个线程持有的对象会因为这个线程终结变为没有被任何线程引用，则在下次GC时这些对象占用空间会被释放掉
 * B线程OOM后，A线程继续执行
 * B线程OOM后，在GC时会释放掉之前由B线程持有的list占用的空间
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
