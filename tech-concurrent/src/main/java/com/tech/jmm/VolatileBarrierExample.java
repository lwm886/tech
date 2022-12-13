package com.tech.jmm;

/**
 * 内存屏障禁止特定重排序
 * @author lw
 * @since 2022-12-12
 */
public class VolatileBarrierExample {
    int a;
    volatile int v1=1;
    volatile int v2=2;

    void readAndWrite(){
        int i=v1; //第一个volatile读
        int j=v2; //第二个volatile读
        a=v1+v2; //普通写
        v1=i+1; //第一个volatile写
        v2=j*2; //第二个volatile写
    }
//    第一个volatile读 -- LoadLoad -- 第二个volatile读 -- LoadStore --普通写 -- StoreStore --
//    第一个volatile写 -- StoreStore -- 第二个volatile写 -- StoreLoad
}
