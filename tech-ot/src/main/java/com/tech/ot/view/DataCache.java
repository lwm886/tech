package com.tech.ot.view;

import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @author lw
 * @since 2023/7/28
 */
public class DataCache {
    //缓存近1s的数据
    public static final Deque<String> queue = new ConcurrentLinkedDeque<>();
}
