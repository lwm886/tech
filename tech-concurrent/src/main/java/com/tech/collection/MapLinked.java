package com.tech.collection;

/**
 * @author lw
 * @since 2023-04-07
 */
public class MapLinked {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(new MapRunner()).start();
        }
    }
}
