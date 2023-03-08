package com.tech.synchronizer;

/**
 * @author lw
 * @since 2023-03-07
 */
public class SyncMethod {
    public synchronized void method(String[] args) {
        System.out.println("Sync Method");
    }
}
