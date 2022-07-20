package com.tech.jvm.opt;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author lw
 * @since 2022/7/15
 * OOM时导出内存快照
 * -Xms5m -Xmx5m -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=C:\tmp\jvm.dump
 */
public class OOMTest {

    public static void main(String[] args) {
        List<Object> list=new ArrayList<>();

        int i=0;
        while (true){
            list.add(new User(i++, UUID.randomUUID().toString()));
        }

    }
}

@Data
class User{
    private int id;
    private String name;

    User(){

    }

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
