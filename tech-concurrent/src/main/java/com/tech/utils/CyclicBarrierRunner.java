package com.tech.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * @author lw
 * @since 2023/4/5
 */
@Slf4j
public class CyclicBarrierRunner implements Runnable{
    private CyclicBarrier cyclicBarrier;
    private int index;

    public CyclicBarrierRunner(CyclicBarrier cyclicBarrier, int index) {
        this.cyclicBarrier = cyclicBarrier;
        this.index = index;
    }

    @Override
    public void run() {
        log.info(index+"");
        index--;
        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(11, () -> {
            log.info("全部线程到达屏障，开始执行任务");
        });
        for (int i = 0; i < 10; i++) {
            new Thread(new CyclicBarrierRunner(cyclicBarrier,i)).start();
//            TimeUnit.MILLISECONDS.sleep(10);
        }
        cyclicBarrier.await();
        log.info("全部执行完成");
    }
}
