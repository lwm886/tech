package com.tech.jvm.mem;

import lombok.Data;

/**
 * @Author lw
 * @Date 2022/6/8
 * @Description
 */
@Data
public class User1 {
    private int id;
    private String name;

    public User1(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    protected void finalize() throws Throwable {
        OOMTest.lists.add(this);
        System.out.println("关闭资源，userId="+id+"即将被回收");
    }
}
