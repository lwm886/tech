package com.tech.ot.oom;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author lw
 * @since 2023/8/11
 */
public class ThreadPoolExecutorOOm {
    
    public static void main(String[] args) throws InterruptedException {
        appRunner(); //其它线程出现OOM，当前线程依旧继续执行，只是会受到频繁GC时STW影响。
        taskRunner(); //运行这个方法的线程出现OOM，这个线程会被终结掉，list会被下轮GC时释放掉。
    }
    
    private static void taskRunner() throws InterruptedException {
        List<byte[]> list=new ArrayList<>();
        new Thread(()->{
            for (;;) {
                list.add(new byte[1000]);
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"task-runner-01").start();
    }

    private static void appRunner() {
        new Thread(()->{
            while (true){
                System.out.println("app run");
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"app-runner").start();
    }
}
