package com.tech.unsafe;

import sun.misc.Unsafe;

/**
 * @author lw
 * @since 2023-04-07
 */
public class AllocateMemoryAccess {
    public static void main(String[] args) {
        Unsafe unsafe = UnsafeInstance.reflectGetUnsafe();
        long oneHundred=Long.MAX_VALUE;
        byte size=8;
        //按字节数分配内存，返回内存地址
        long memoryAddress = unsafe.allocateMemory(size);
        System.out.println("address:->"+memoryAddress);

        //将数据写入分配的内存中
        unsafe.putAddress(memoryAddress,oneHundred);

        //内存中读取数据
        long realVal = unsafe.getAddress(memoryAddress);
        System.out.println("value:"+realVal);

        unsafe.freeMemory(memoryAddress);
    }
}
