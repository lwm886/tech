package com.tech.jvm.mat2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author lw
 * @since 2022/8/4
 */
public class EmptyListDemo {
    public static void main(String[] args) {
        EmptyValueContainerList emptyValueContainerList = new EmptyValueContainerList();
        FilledValueContainerList filledValueContainerList = new FilledValueContainerList();
        System.out.println("start sleep ... ");
        try {
            Thread.sleep(50*1000*1000);
        } catch (InterruptedException e) {
            System.exit(1);
        }
    }
}

class EmptyValueContainer{
    List<Integer> value1=new ArrayList(10);
    List<Integer> value2=new ArrayList(10);
    List<Integer> value3=new ArrayList(10);
}

class EmptyValueContainerList{
    List<EmptyValueContainer> list=new ArrayList(500*1000);

    public EmptyValueContainerList(){
        for (int i = 0; i < 500 * 1000; i++) {
            list.add(new EmptyValueContainer());
        }
    }
}

class FilledValueContainer{
    List<Integer> value1=new ArrayList(10);
    List<Integer> value2=new ArrayList(10);
    List<Integer> value3=new ArrayList(10);

    public FilledValueContainer init(){
        value1.addAll(Arrays.asList(1,3,5,7,9));
        value2.addAll(Arrays.asList(2,4,6,8,10));
        value1.addAll(Arrays.asList(1,1,1,1,1,1,1,1,1,1));
        return this;
    }
}

class FilledValueContainerList{
    List<FilledValueContainer> list=new ArrayList(500);

    public FilledValueContainerList(){
        for (int i = 0; i < 500; i++) {
            list.add(new FilledValueContainer().init());
        }
    }
}