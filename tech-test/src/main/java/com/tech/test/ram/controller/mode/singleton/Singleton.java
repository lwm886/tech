package com.tech.test.ram.controller.mode.singleton;

/**
 * @author lw
 * @since 2023/7/5
 */
public class Singleton {
    private Singleton() {
    }

    private static class LazyHolder {
        final static Singleton SINGLETON = new Singleton();
    }

    public static Singleton getInstance() {
        return LazyHolder.SINGLETON;
    }
}
