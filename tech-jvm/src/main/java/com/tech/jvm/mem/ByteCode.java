package com.tech.jvm.mem;

import java.io.Serializable;

/**
 * @Author lw
 * @Date 2022/6/9
 * @Description javap -verbose ByteCode.class
 */
public class ByteCode implements Serializable {

    private String userName;

    private final int i=100000;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
