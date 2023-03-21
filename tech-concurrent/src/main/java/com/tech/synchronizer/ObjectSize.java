package com.tech.synchronizer;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author lw
 * @since 2023-03-15
 */
public class ObjectSize {
    int id;        // 4B
//    String name="aaaaaaaaaaaaaaaaaaaaaaa";   // 4B 如果关闭对象指针压缩-XX:-UseCompressedOops，则占用8B
    byte b;        // 1B
    long l;
//    Object o;

    public static void main(String[] args) {
        ObjectSize o = new ObjectSize();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
    }
}
