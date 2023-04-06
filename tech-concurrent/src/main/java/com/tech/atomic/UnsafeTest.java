package com.tech.atomic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author lw
 * @since 2023-04-06
 */
@Data
@AllArgsConstructor
@Slf4j
public class UnsafeTest {
    private String name;
    private volatile int age;



    public static void main(String[] args) throws NoSuchFieldException {
        UnsafeTest unsafeTest = new UnsafeTest("张三",1);
        Unsafe unsafe = getUnsafe();
        long ageOffset = unsafe.objectFieldOffset(unsafeTest.getClass().getDeclaredField("age"));
        System.out.println(ageOffset);
        boolean b = unsafe.compareAndSwapInt(unsafeTest, ageOffset, 1, 100);
        if(b){
            System.out.println("cas success");
            System.out.println(unsafeTest.getAge());
        }



        System.out.println(ClassLayout.parseInstance(unsafeTest).toPrintable());
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
