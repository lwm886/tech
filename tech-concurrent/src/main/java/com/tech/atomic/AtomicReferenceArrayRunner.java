package com.tech.atomic;

import java.util.concurrent.atomic.AtomicReferenceArray;

/**
 * @author lw
 * @since 2023-04-06
 */
public class AtomicReferenceArrayRunner {
   static Node[] nodes=new Node[]{new Node(1),new Node(2)};

    public static void main(String[] args) {
        AtomicReferenceArray<Node> ato = new AtomicReferenceArray<Node>(nodes);
        ato.set(0,new Node(3));

        System.out.println(ato.get(0).getId());
        System.out.println(nodes[0].getId());
    }
}
