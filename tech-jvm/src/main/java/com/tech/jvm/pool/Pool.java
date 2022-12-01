package com.tech.jvm.pool;

/**
 * @author lw
 * @since 2022/8/1
 */
public class Pool {
    public static void main(String[] args) {
        //1 在JDK8会创建5个对象，串池中有2个he、llo，堆中其他区域有3个he、llo、hello
        //  在s1调用intern时，由于s1中不存在hello，会在串池中创建指向堆中hello的引用,然后返回串池中的引用
        //  两个引用都指向堆中同一个对象hello
//        String s1=new String("he")+new String("llo");
//        String s2 = s1.intern();
//        System.out.println(s1 == s2);  //true

        //2 s0和s1都是指向串池中的zhuge，加号连接的两个字面量,其值在编译期已经确定，会被编译器优化为连接后的内容，
        // 因此s2也是指向串池中的同一个zhuge
//        String s0="zhuge";
//        String s1="zhuge";
//        String s2="zhu"+"ge";
//        System.out.println(s0 == s1); //true
//        System.out.println(s0 == s2); //true

        //3 s0指向串池中字符串，s1指向堆中字符串,s2指向堆中字符串
//           s1和s2都使用了new，会在堆中开辟新的空间不是同一个字符串
//        String s0="zhuge";
//        String s1=new String("zhuge");
//        String s2="zhu"+new String("ge");
//        System.out.println(s0 == s1); //false
//        System.out.println(s0 == s2); //false
//        System.out.println(s1 == s2); //false

        //4
//        String a="a1";
//        String b="a"+1;
//        System.out.println(a == b); //true +连接的字面量会被编译器优化为连接后的内容，都是指向串池中的内容
//        String a = "atrue";
//        String b = "a" + true;
//        System.out.println(a == b); //true 同上
//        String a="a3.4";
//        String b="a"+3.4;
//        System.out.println(a == b); //true 同上

        //5 +连接的是一个字面量和一个变量，它的值在编译期无法确定，不会被编译器优化,会创建StringBuilder，通过append拼接，
        // 然后调用StringBuilder的toString，在实现上会new String
//        String a="ab";
//        String bb="b";
//        String b="a"+bb;
//        System.out.println(a == b); //false

        //6 +连接一个字面量与一个final修饰的变量，此时其值在编译期间已经确定，会被编译器优化连接后的值，都是指向串池中同一个字符串
//        String a= "ab";
//        final String bb="b";
//        String b="a"+bb;
//        System.out.println(a == b); //true

        //7 变量bb的值在编译器无法确认，需要程序运行时调用方法，不会被编译器直接优化，此时+会通过StringBuilder拼接，拼接完成后调用
//        toString 此时会new String
//        String a="ab";
//        final String bb = getBB();
//        String b="a"+bb;
//        System.out.println(a == b); //false

        //8 +连接的变量其值在编译期无法确认，不会被编译器优化，此时会通过StringBuilder拼接，然后toString，在toString时会new String
//        String s="a"+"b"+"c";
//        String a="a";
//        String b="b";
//        String c="c";
//        String s1=a+b+c;
//        System.out.println(s == s1); //false

        //9 str2在堆中，调用intern时，串池中不存在这个字符串，会维护一个指向计算机技术的引用，两个引用都指向了同一个堆中字符串
//        String str2 = new StringBuilder("计算机").append("技术").toString();
//        System.out.println(str2 == str2.intern()); //true

        //10 和上面不同的是java关键字，已经存在于串池中，在调用intern时，返回指向串池中java的引用,str1是指向堆中其他地方java的引用，非同一个
//        String str1 = new StringBuilder("ja").append("va").toString();
//        System.out.println(str1 == str1.intern()); //false

        //11 首先在串池中创建一个test，然后在堆中创建一个test,s1指向堆中内容，调用intern返回指向串池中字符串的引用，不是同一个
//        String s1=new String("test");
//        System.out.println(s1 == s1.intern()); //false

        //12 同上
//        String s2=new StringBuilder("abc").toString();
//        System.out.println(s2 == s2.intern()); //false

        //13 -128到127会使用对象池，提升性能减少内存开销
//        Integer i1=127;
//        Integer i2=127;
//        System.out.println(i1 == i2);// true

        //14 大于127不会使用对象池
//        Integer i3=128;
//        Integer i4=128;
//        System.out.println(i3 == i4);// false

//        15 new 在堆中其他地方分配内存
//        Integer i5 = new Integer(127);
//        Integer i6 = new Integer(127);
//        System.out.println(i5 == i6);// false

        //16 boolean实现了对象池，都指向对象池中同一个值
        Boolean boolean1=true;
        Boolean boolean2=true;
        System.out.println(boolean1 == boolean2);// true

        //浮点类型没有实现对象池，堆中创建了两个Double
        Double d1=1.0;
        Double d2=1.0;
        System.out.println(d1 == d2); // false
    }

    private static String getBB() {
        return "b";
    }


}
