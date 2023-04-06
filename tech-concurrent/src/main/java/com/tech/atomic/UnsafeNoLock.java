package com.tech.atomic;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.CountDownLatch;

/**
 * @author lw
 * @since 2023-04-06
 */
public class UnsafeNoLock {
    private volatile int c = 0;
    private CountDownLatch countDownLatch=new CountDownLatch(10);

    public static void main(String[] args) throws NoSuchFieldException, InterruptedException {
        //测试10次
        for (int i = 0; i < 10; i++) {
            test();
        }
    }

    public static void test() throws NoSuchFieldException, InterruptedException {
        UnsafeNoLock obj = new UnsafeNoLock();
        Unsafe unsafe = getUnsafe();
        long cOffset = unsafe.objectFieldOffset(obj.getClass().getDeclaredField("c"));
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    noLockIncr(obj, unsafe, cOffset);
//                    obj.c=obj.c+1;
                }
                obj.countDownLatch.countDown();
            }).start();
        }
        obj.countDownLatch.await();

        System.out.println(obj.c);

    }

    private static void noLockIncr(UnsafeNoLock obj, Unsafe unsafe, long cOffset) {
        int oldVal;
        do {
            oldVal = unsafe.getIntVolatile(obj, cOffset);
        } while (!unsafe.compareAndSwapInt(obj, cOffset, oldVal, oldVal + 1));
    }


    private static Unsafe getUnsafe() {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            return (Unsafe) theUnsafe.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
