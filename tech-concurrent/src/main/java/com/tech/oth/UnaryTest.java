package com.tech.oth;

import java.util.function.UnaryOperator;

/**
 * @author lw
 * @since 2023-04-07
 */
public class UnaryTest {
    public static void main(String[] args) {
        UnaryOperator<Integer> fun=p->{
            System.out.println(p);
            return p+1;
        };
        System.out.println(fun.apply(100000));
    }
}
