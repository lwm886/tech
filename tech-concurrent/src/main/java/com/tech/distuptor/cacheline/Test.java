package com.tech.distuptor.cacheline;

import com.tech.unsafe.UnsafeInstance;
import sun.misc.Unsafe;

/**
 * @author lw
 * @since 2023-04-20
 */
public class Test {
    private long c=0;

    public static void main(String[] args) throws NoSuchFieldException {
        Test test = new Test();
        Unsafe unsafe = UnsafeInstance.reflectGetUnsafe();
        long offset = unsafe.objectFieldOffset(Test.class.getDeclaredField("c"));
        unsafe.putOrderedLong(test,offset,100L);
        System.out.println(unsafe.getLongVolatile(test,offset));

    }
}
