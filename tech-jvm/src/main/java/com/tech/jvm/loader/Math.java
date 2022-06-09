package com.tech.jvm.loader;

/**
 * @Author lw
 * @Date 2022/5/18
 * @Description
 */
public class Math {
    public static final int initData = 666;
    public static User user = new User();

    public int compute() {
        int a = 1;
        int b = 5;
        int c = (a + b) * 10;
        return c;
    }

    public static void main(String[] args) {
        String s="abc";
        Math math = new Math();
        math.compute();
        System.out.println(s);
    }
}
