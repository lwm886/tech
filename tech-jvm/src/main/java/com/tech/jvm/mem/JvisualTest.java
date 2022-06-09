package com.tech.jvm.mem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author lw
 * @Date 2022/5/30
 * @Description
 *
 */
public class JvisualTest {
    public static void main(String[] args) throws InterruptedException {
        List<User> list = new ArrayList<>();
        while (true){
            list.add(new User());
            TimeUnit.MICROSECONDS.sleep(100);
        }
    }
}
