package com.tech.test.ram.resource.memory;

import java.util.concurrent.TimeUnit;

/**
 * @author lw
 * @since 2023/8/2
 */
public class VarArea {
    
    private static byte[] bs=new byte[1024*1024*100]; //占用堆内存
    
    public static void main(String[] args) throws InterruptedException {
        int i=1024*1024*10;
        int s=0;
        for(;;){
            s+=i;
            bs=new byte[s];
            TimeUnit.SECONDS.sleep(10);
            System.out.println(i/(1024*1024)+"M");
        }
    }
}
