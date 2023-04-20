package com.tech.distuptor.cacheline;

/**
 * 根据操作系统的空间局部系统原理，CPU读取内存中一个变量时会将这个所在缓存行上的变量一并读取，这是操作系统对缓存行的优化
 * 创建一个二维数组，按行读取（内存连续）的效率要优于按列读取（内存不连续）
 *
 * @author lw
 * @since 2023-04-19
 */
public class CacheLineRunner {
    static long[][] arr = new long[1024 * 1024][8];

    public static void main(String[] args) {
        for (int i = 0; i < 1024 * 1024; i++) {
            for (int j = 0; j < 8; j++) {
                arr[i][j] = 1;
            }
        }
        long lineBeginTime = System.currentTimeMillis();
        long sum = 0;
        for (int i = 0; i < 1024 * 1024; i++) {
            for (int j = 0; j < 8; j++) {
                sum = arr[i][j];
            }
        }
        System.out.println("顺序读取时间：" + (System.currentTimeMillis() - lineBeginTime));

        long columnBeginTime = System.currentTimeMillis();
        long sum1 = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 1024 * 1024; j++) {
                sum1 = arr[j][i];
            }
        }
        System.out.println("非顺序读取时间：" + (System.currentTimeMillis() - columnBeginTime));

    }
}
