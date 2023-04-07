package com.tech.atomic;


import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @author lw
 * @since 2023-04-06
 */
public class AtomicIntegerFieldUpdaterRunner {
    static AtomicIntegerFieldUpdater ato=AtomicIntegerFieldUpdater.newUpdater(Node.class,"id");

    public static void main(String[] args) {
        Node node = new Node(1);
        System.out.println(ato.getAndIncrement(node));
        System.out.println(ato.getAndIncrement(node));
        System.out.println(ato.get(node));
    }
}
