package com.tech.jmm;

/**
 * volatile 可见性
 *
 * @author lw
 * @since 2022-12-01
 */
public class VolatileVisibilitySample {
    volatile boolean initFlag = false;

    public void save() {
        this.initFlag = true;
        System.out.println("线程：" + Thread.currentThread().getName() + " 修改共享变量initFlag");
    }

    public void load() {
        while (!initFlag) {
        }
        System.out.println("线程 " + Thread.currentThread().getName() + " 嗅探到initFlag的状态的改变");
    }

    public static void main(String[] args) {
        VolatileVisibilitySample sample = new VolatileVisibilitySample();

        Thread threadA = new Thread(() -> {
            sample.save();
        }, "threadA");
        Thread threadB = new Thread(() -> {
            sample.load();
        }, "threadB");

        threadB.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadA.start();
    }
}
