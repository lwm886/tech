package com.tech.jvm.loader;

/**
 * @Author lw
 * @Date 2022/5/19
 * @Description
 */
public class TestDynamicLoad {
    static {
        System.out.println("***************TestDynamicLoad*****************");
    }

    public static void main(String[] args) {
        new A();
        System.out.println("*****************load test******************");
        B b = null;
    }
}

class A{
    static {
        System.out.println("****************load A***********************");
    }

    public A(){
        System.out.println("****************initial A***********************");

    }
}

class B{
    static {
        System.out.println("****************load B***********************");
    }

    public B(){
        System.out.println("****************initial B***********************");

    }
}