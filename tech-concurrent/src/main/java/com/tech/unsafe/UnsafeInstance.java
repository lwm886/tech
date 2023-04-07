package com.tech.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author lw
 * @since 2023-04-07
 */
public class UnsafeInstance {

    public static Unsafe reflectGetUnsafe(){
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
