package com.tech.jvm.mat;
import jdk.internal.org.objectweb.asm.Opcodes;

import java.util.ArrayList;

/**
 * @author lw
 * @since 2022/8/3
 */
public class ThreadAndListHolder extends ClassLoader implements Opcodes {
    private static Thread innerThread1;
    private static Thread innerThread2;
    private static final SameContentWrapperContainerProxy sameContentWrapperContainerProxy=new SameContentWrapperContainerProxy();
    static {
        //启用两个线程作为 GC Roots
        innerThread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                SameContentWrapperContainerProxy proxy=sameContentWrapperContainerProxy;
                try {
                    Thread.sleep(60*60*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        innerThread1.setName("ThreadAndListHolder-thread-1");
        innerThread1.start();

        innerThread2=new Thread(new Runnable() {
            @Override
            public void run() {
                SameContentWrapperContainerProxy proxy = proxy =sameContentWrapperContainerProxy;
                try {
                    Thread.sleep(60*60*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        innerThread2.setName("ThreadAndListHolder-thread-2");
        innerThread2.start();
    }
}

class IntArrayListWrapper{
    private ArrayList<Integer> list;
    private String name;

    public IntArrayListWrapper(ArrayList<Integer> list, String name) {
        this.list = list;
        this.name = name;
    }
}

class SameContentWrapperContainer{
    //两个Wrapper内部指向同一个ArrayList,方便学习Dominator tree
    IntArrayListWrapper intArrayListWrapper1;
    IntArrayListWrapper intArrayListWrapper2;

    public void init(){
        //线程直接支配arrayList,两个IntArrayListWrapper均不支配arrayList，只能线程运行完回收
        ArrayList<Integer> arrayList = generateSeqIntList(10 * 1000 * 1000, 0);
        intArrayListWrapper1=new IntArrayListWrapper(arrayList,"IntArrayListWrapper-1");
        intArrayListWrapper2=new IntArrayListWrapper(arrayList,"IntArrayListWrapper-2");
    }

    private static ArrayList<Integer> generateSeqIntList(int size,int startValue){
        ArrayList<Integer> list = new ArrayList<>(size);
        for (int i = startValue; i < startValue+size; i++) {
            list.add(i);
        }
        return list;
    }
}

class  SameContentWrapperContainerProxy{
    SameContentWrapperContainer sameContentWrapperContainer;

    public SameContentWrapperContainerProxy() {
        SameContentWrapperContainer container = new SameContentWrapperContainer();
        container.init();
        sameContentWrapperContainer=container;
    }
}