package com.tech.forkjoinpool.recursivetask;

import lombok.extern.slf4j.Slf4j;
import sun.java2d.pipe.AAShapePipe;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author lw
 * @since 2023-04-16
 */
@Slf4j
public class MoreThreadRunner {
    private static List<Thread> list = new ArrayList<>();

    public static void main(String[] args) {
        for (int i = 0; i < 100000000; i++) {
            Thread thread = new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("run...");
            });
            list.add(thread);
            thread.start();
            System.out.println(list.size());
        }
        list.forEach(p -> {
                    System.out.println(p.getId());
                }
        );
    }
}
