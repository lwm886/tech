package com.tech.synchronizer;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

/**
 * @author lw
 * @since 2023-03-20
 */
public class SyncHashCode {
    public static void main(String[] args) throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        Object o = new Object();
        System.out.println("1 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> :");
        //匿名偏向锁
        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        synchronized (o) {
            System.out.println("2 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> :");
            //偏向锁
            System.out.println(ClassLayout.parseInstance(o).toPrintable());

            //同步块内打印hashCode 偏向锁升级为轻量级锁
//            System.out.println(o.hashCode());
//            System.out.println("3 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> :");
//            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }

        System.out.println(o.hashCode());
        System.out.println(ClassLayout.parseInstance(o).toPrintable());


        synchronized (o){
            System.out.println("3 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> :");
            //代码块外打印hashCode 轻量级锁
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }

    }
}
