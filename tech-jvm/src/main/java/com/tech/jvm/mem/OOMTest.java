package com.tech.jvm.mem;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Author lw
 * @Date 2022/6/8
 * @Description
 */
public class OOMTest {
    static List<Object> lists=new ArrayList<>();
    public static void main(String[] args) {
        List<Object> list = new ArrayList<>();
        int i=0;
        int j=0;
        while (true){
            list.add(new User1(i++, UUID.randomUUID().toString()));
            new User1(j--,UUID.randomUUID().toString());
        }
    }
}
