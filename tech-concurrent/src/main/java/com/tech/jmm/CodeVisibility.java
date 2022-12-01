package com.tech.jmm;

import lombok.extern.slf4j.Slf4j;

/**
 * 可见性测试
 *
 * @author lw
 * @since 2022-11-16
 */
@Slf4j
public class CodeVisibility {

    private static boolean initFlag = false;

    private static long counter = 0L;

    public static void refresh(){
        log.info("refresh data ... ");
        initFlag = true;
        log.info("refresh data success ... ");
    }

    public static void main(String[] args) {
        new Thread(()->{
            while (!initFlag){
//                System.out.println(1);
                counter++;
            }
            log.info("线程："+Thread.currentThread().getName());
        },"threadA").start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            refresh();
        },"threadB").start();

    }
}
