package com.tech.jvm.mem;

/**
 * @Author lw
 * @Date 2022/6/10
 * @Description
 * 非字符串字面量直接相加，底层会使用StringBuilder拼接；
 * 一般在堆中创建字符串时，堆中会创建一个字符数组，字符数组指向常量池中的字符串；
 * StringBuilder拼接生成的字符串不会存入常量池，需要intern入池（常量池不存在该字符串时才会存入字符串）返回常量池中字符串引用
 */
public class StringCompare {
    public static void main(String[] args) {
        //1. true 指向的是运行时常量池中同一个文本字符串值
//        String test="abc";
//        String test1="abc";
//        System.out.println(test == test1);

        //2. true 编译器会进行优化，将a+b+c优化为abc,指向的是运行时常量池中同一个文本字符串值
//        String test="a"+"b"+"c";
//        String test1="abc";
//        System.out.println(test == test1);

        //3. false test指向运行时常量池，test1指向堆中字符数组，堆中字符数组指向运行时常量池中字符串，test和test1指向不同
//        String test="abc";
//        String test1=new String("abc");
//        System.out.println(test == test1);

        //4. false test指向堆中一块内存，test1指向堆中另外一块内存，虽然这两块内存值都指向运行时常量池，但是test和test1指向不同
//        String test=new String("abc");
//        String test1=new String("abc");
//        System.out.println(test == test1);

        //5. false 会采用StringBuilder拼接，new String(c),在堆中分配内存字符数组，字符数组指向行时常量池中c，ab存放在运行时常量池中，StringBuilder运算得到abc,
        // test指向stringBuilder执行append进行toString后的结果对象，test1指向运行时常量池，指向不同
//        String test=new String("c")+"ab";
//        String test1=new String("abc");
//        System.out.println(test==test1);

        //6. false 会采用StringBuilder拼接 intern将一个字符串存入运行时常量池（运行时常量池字符串不存在时）
        // 并返回字符串在运行时常量池地址，test指向堆内存，test1指向运行时常量池内存，指向不同
//        String test="ab"+new String("c");
//        String test1="abc";
//        String test2=test.intern();
//        System.out.println(test==test1);

        //7. true 会采用StringBuilder拼接 intern将一个字符串存入运行时常量池（运行时常量池字符串不存在时）
        // 并返回字符串在运行时常量池地址，都指向同一个运行时常量池中地址
        String test="ab"+new String("c");
        String test1="abc";
        String test2=test.intern();
        System.out.println(test1==test2);
    }
}
