package com.tech.synchronizer;

/**
 * @author lw
 * @since 2023-02-16
 */
public class LockOnClass {
    static int stock;
    public static synchronized void decrStock(){
        System.out.println(--stock);
    }
    public static synchronized void cgg(){
        System.out.println();
    }

    public static void main(String[] args) {
        LockOnClass.decrStock();
    }
}
