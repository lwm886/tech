package com.tech.aop.d;

import java.util.Arrays;
import java.util.List;

/**
 * 责任链模式
 * @author lw
 * @since 2023-05-17
 */
public class FilterTest {
    public static void main(String[] args) {
        List<Filter> filters= Arrays.asList(new FilterA(),new FilterB(),new FilterC());
        FilterInvocation filterInvocation = new FilterInvocation(filters);
        filterInvocation.process();
    }
}
