package com.tech.collection;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lw
 * @since 2023-04-07
 */
public class MapRunner implements Runnable {

    private static Map<Integer, Integer> map = new HashMap<>(2);

    private static AtomicInteger ato = new AtomicInteger();

    @Override
    public void run() {
        while (ato.get() < 1000000) {
            map.put(ato.get(), ato.get());
            ato.incrementAndGet();
        }
    }
}
