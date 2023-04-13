package com.tech.queue;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

/**
 * -Xms300M -Xmx300M -XX:+PrintGCDetails
 * @author lw
 * @since 2023-04-12
 */
public class CopyOnWriteArrayListRunner {
    public static void main(String[] args) throws InterruptedException {
        CopyOnWriteArrayList<byte[]> list=new CopyOnWriteArrayList();
        for (int i = 0; i < 100000; i++) {

            if(i>5){
              list.add(new byte[5*1024*1024]);
                System.out.println(list.size());
            }else{
                list.add(new byte[50*1024*1024]);
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(list.size());
    }
}
