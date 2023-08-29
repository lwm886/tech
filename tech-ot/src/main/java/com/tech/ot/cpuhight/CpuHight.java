package com.tech.ot.cpuhight;

/**
 * 
 * "cpu-high" #12 prio=5 os_prio=0 tid=0x000000001fc71000 nid=0x80bc runnable [0x00000000203af000]
 *    java.lang.Thread.State: RUNNABLE
 *         at com.tech.ot.cpuhight.CpuHight.lambda$main$0(CpuHight.java:10)
 *         at com.tech.ot.cpuhight.CpuHight$$Lambda$1/1237514926.run(Unknown Source)
 *         at java.lang.Thread.run(Thread.java:748)
 * 
 * "main" #1 prio=5 os_prio=0 tid=0x00000000033e5800 nid=0x6d14 runnable [0x000000000325f000]
 *    java.lang.Thread.State: RUNNABLE
 *         at com.tech.ot.cpuhight.CpuHight.main(CpuHight.java:14)
 * 
 * 对于这种像无限循环占用CPU比较高的线程，在使用jstack等工具查看线程栈时，会得到线程在runable（这里表示就绪或运行中，并未区分，因为时间片很短，此时这两种状态都用runable表示）状态下的线程栈
 * 
 * 可以看到线程栈执行的是哪个类的哪个位置，定位程序
 *         
 * @author lw
 * @since 2023/8/20
 */
public class CpuHight {
    public static void main(String[] args) {
        for(int i=0;i<1000;i++){
            new Thread(()->{
                while (true){

                }
            },"cpu-high").start();
        }
        while (true){
            
        }
    }
}
