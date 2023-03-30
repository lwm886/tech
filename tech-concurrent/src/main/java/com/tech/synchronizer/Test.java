package com.tech.synchronizer;

/**
 * 粗化：
 * JVM在JIT编译时，检测到对一个对象执行了连续的加锁解锁操作，它可能会将多个加锁解锁操作和并成一个加锁解锁操作，减少加减锁的开销，提升程序执行效率。如StringBuffer的append方法使用了synchronized锁同步，如果连续的调用append方法，则可能会触发锁粗化，这些append方法使用一个锁来同步。
 *
 * @author lw
 * @since 2023-03-21
 */
public class Test {
    public static void main(String[] args) {
       StringBuffer sb=new StringBuffer();
       for(int i=0;i<100;i++){
           sb.append(i);
       }
        System.out.println(sb);
    }
}
