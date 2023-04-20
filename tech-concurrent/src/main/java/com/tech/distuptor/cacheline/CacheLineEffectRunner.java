package com.tech.distuptor.cacheline;

import sun.misc.Contended;

/**
 * 当两个变量在一个缓存行时，如果一个频繁写，另外一个频繁读，可能写操作会频繁的导致缓存行失效，导致读操作不断的去内存读取缓存行，影响性能，这就是伪共享
 *
 * 对象填充，消除伪共享，耗时大大缩短
 *
 * JDK8可以通过Contented注解，让对象的字段在不同的缓存行，消除为伪共享，这种方式默认JVM没有开启 需要在JVM启动参数加上-XX:-RestrictContended
 * @author lw
 * @since 2023-04-19
 */
public class CacheLineEffectRunner {
    public static void main(String[] args) throws InterruptedException {
        testP(new P());
//        System.out.println("对象字段填充，消除伪共享");
//        testP(new P1());
        System.out.println("通过JDK8注解 @Contended，让各个字段处于不同的缓存行，消除伪共享");
        testP(new P2());
    }

    //伪共享
    public static void testP(P p) throws InterruptedException {
        long begin=System.currentTimeMillis();

        Thread t1 = new Thread(
                () -> {
                    for(int i=0;i<1000000000;i++){
                        p.x++;
                    }
                }
        );
        Thread t2 = new Thread(
                () -> {
                    for(int i=0;i<1000000000;i++){
                        p.y++;
                    }
                }
        );

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(System.currentTimeMillis()-begin);
        System.out.println(p);
    }
    //消除伪共享
    public static void testP(P1 p) throws InterruptedException {
        long begin=System.currentTimeMillis();

        Thread t1 = new Thread(
                () -> {
                    for(int i=0;i<1000000000;i++){
                        p.x++;
                    }
                }
        );
        Thread t2 = new Thread(
                () -> {
                    for(int i=0;i<1000000000;i++){
                        p.y++;
                    }
                }
        );

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(System.currentTimeMillis()-begin);
        System.out.println(p);
    }
    public static void testP(P2 p) throws InterruptedException {
        long begin=System.currentTimeMillis();

        Thread t1 = new Thread(
                () -> {
                    for(int i=0;i<1000000000;i++){
                        p.x++;
                    }
                }
        );
        Thread t2 = new Thread(
                () -> {
                    for(int i=0;i<1000000000;i++){
                        p.y++;
                    }
                }
        );

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(System.currentTimeMillis()-begin);
        System.out.println(p);
    }
}
//会产生伪共享
class P {
    volatile long x;
    volatile long y;
}
//消除伪共享 字段填充
class P1 {
    volatile long x;
    //填充7个long，让x y不在同一个缓存行
    long a,b,c,d,e,f,g;
    volatile long y;
}
//消除伪共享 通过Contented注解，让字段在单独的缓存行里 需要在JVM启动参数加上-XX:-RestrictContended
class P2 {
    @Contended
    volatile long x;
    @Contended
    volatile long y;
}