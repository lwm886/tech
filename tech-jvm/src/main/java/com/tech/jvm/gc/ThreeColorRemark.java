package com.tech.jvm.gc;

/**
 * @Author lw
 * @Date 2022/6/15
 * @Description
 */
public class ThreeColorRemark {
    public static void main(String[] args) {
        A a=new A();

        D d = a.b.d;
        a.b.d=null;
        a.d=d;
    }
}

class A {
    B b = new B();
    D d = null;
}

class B {
    C c = new C();
    D d = new D();
}

class C {

}

class D {

}