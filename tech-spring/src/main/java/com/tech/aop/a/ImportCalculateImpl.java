package com.tech.aop.a;

import org.springframework.stereotype.Component;

/**
 * @author lw
 * @since 2023-05-17
 */
@Component
public class ImportCalculateImpl implements ImportCalculate {
    @Override
    public int sum(int a, int b) {
        return a+b;
    }
}
