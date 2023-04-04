package com.tech.jmm;

/**
 * @author lw
 * @since 2022/12/9
 */
public class DoubleCheckLock {
    
    private volatile static DoubleCheckLock instance;

    private DoubleCheckLock() {
    }

    public static DoubleCheckLock getInstance() {
        if (instance == null) {
            synchronized (DoubleCheckLock.class) {
                if (instance == null) {
                    instance = new DoubleCheckLock();
                }
            }
        }
        return instance;
    }
}
