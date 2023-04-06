package com.tech.atomic;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author lw
 * @since 2023-04-06
 */
public class LoadUnsafeTest {
    public static void main(String[] args) {
        //报错
//        Unsafe unsafe = Unsafe.getUnsafe();
//        System.out.println(unsafe);
    }


    public static Unsafe getUnsafe(){
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            return (Unsafe)theUnsafe.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
