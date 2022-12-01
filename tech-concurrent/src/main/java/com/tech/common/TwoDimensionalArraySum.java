package com.tech.common;

/**
 * 空间局部性原理
 * 当存储器中的一个位置被引用时，将来它附近的位置也可能会被引用，在读取缓存时这个位置和它附件的位置的数据会被一次性全部读取
 * 测试结果：第一种求和方式要比第二种求和方式花费的时间少，因为空间局部性，会把访问的变量所在的缓存行中这个位置和附件位置所有的变量一次性全部读取，减少读取次数，性能更高。
 * @author lw
 * @since 2022-11-14
 */
public class TwoDimensionalArraySum {
    private static final int RUNS = 100;
    private static final int DIMENSION_1 = 1024 * 1024;
    private static final int DIMENSION_2 = 6;
    private static long[][] longs;

    public static void main(String[] args) {
        /**
         * 初始化数组
         */
        longs = new long[DIMENSION_1][];
        for (int i = 0; i < DIMENSION_1; i++) {
            longs[i] = new long[DIMENSION_2];
            for (int j = 0; j < DIMENSION_2; j++) {
                longs[i][j] = 1L;
            }
        }
        System.out.println("Array初始化完毕......");

        long sum = 0L;
        long start = System.currentTimeMillis();
        for (int r = 0; r < RUNS; r++) {
            for (int i = 0; i < DIMENSION_1; i++) {
                for (int j = 0; j < DIMENSION_2; j++) {
                    sum += longs[i][j];
                }
            }
        }
        System.out.println("spend time1:" + (System.currentTimeMillis() - start));
        System.out.println("sum1:" + sum);

        sum = 0L;
        start = System.currentTimeMillis();
        for (int r = 0; r < RUNS; r++) {
            for(int j=0;j<DIMENSION_2;j++){
                for(int i=0;i<DIMENSION_1;i++){
                    sum+=longs[i][j];
                }
            }
        }
        System.out.println("spend time2:" + (System.currentTimeMillis() - start));
        System.out.println("sum2:" + sum);


    }

}
