package com.tech.jvm.mem;

import org.openjdk.jol.info.ClassLayout;

/**
 * @Author lw
 * @Date 2022/6/1
 * @Description 计算对象大小 指针压缩
 * 需要添加依赖
 * <dependency>
 *     <groupId>org.openjdk.jol</groupId>
 *     <artifactId>jol-core</artifactId>
 *     <version>0.9</version>
 * </dependency>
 */
public class JOLSample {
    public static void main(String[] args) {
        //对象头[MarkWord 8字节（64位机器），类元信息指针4字节（默认开启压缩）]，对象填充4字节（使得占用空间位8的倍数，计算机存取更高效）
        ClassLayout layout = ClassLayout.parseInstance(new Object());
        System.out.println(layout.toPrintable());
        System.out.println();

        //对象头[MarkWord 8字节（64位机器），类元信息指针4字节（默认开启压缩），数组长度使用4字节存储]
        ClassLayout layout1 = ClassLayout.parseInstance(new int[]{});
        System.out.println(layout1.toPrintable());
        System.out.println();

        ClassLayout layout2 = ClassLayout.parseInstance(new A());
        System.out.println(layout2.toPrintable());


    }

    //JVM默认已经开启类元信息指针压缩,如需关闭 -XX:-UseCompressedClassPointers
    //JVM默认已经开启类对象指针压缩（包含对象指针、类元信息指针）,如需关闭 -XX:-UseCompressedOops
    public static class A{
                       // MarkWord 8B
                       // Klass Pointer 4B,如果关闭类元信息指针压缩-XX:-UseCompressedClassPointers 变为8B
        int id;        // 4B
        String name;   // 4B 如果关闭对象指针压缩-XX:-UseCompressedOops，则占用8B
        byte b;        // 1B
        Object o;      // 4B 如果关闭对象指针压缩-XX:-UseCompressedOops，则占用8B
                       // 实例数据如果不是4的倍数，会发生internal对象填充
    }
}
