package com.tech.aop.d;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lw
 * @since 2023-05-17
 */
public class FilterInvocation {
    private List<Filter> list=new ArrayList<>();
    private int i=0;

    public FilterInvocation(List<Filter> list) {
        this.list = list;
    }

    public void process(){
        if(i==list.size()){
            return;
        }
        Filter filter = list.get(i++);
        filter.invoke(this);
    }
}
