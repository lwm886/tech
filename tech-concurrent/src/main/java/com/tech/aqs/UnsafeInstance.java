package com.tech.aqs;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author lw
 * @since 2023-03-31
 */
public class UnsafeInstance {
    public static Unsafe reflectGetUnsafe() {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return (Unsafe) field.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
