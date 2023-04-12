package com.tech.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author lw
 * @since 2023-04-12
 */
public class BlockingQueueTest {
    public static void main(String[] args) {
        ArrayBlockingQueue<Integer> ab = new ArrayBlockingQueue<Integer>(10);
        LinkedBlockingQueue<Integer> lb = new LinkedBlockingQueue<>();
        System.out.println(lb.poll());
    }
}
