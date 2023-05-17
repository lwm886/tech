package com.tech.aop.d;

/**
 * @author lw
 * @since 2023-05-17
 */
public class FilterB implements Filter {
    @Override
    public void invoke(FilterInvocation invocation) {
        System.out.println("in filterB");
        invocation.process();
    }
}
