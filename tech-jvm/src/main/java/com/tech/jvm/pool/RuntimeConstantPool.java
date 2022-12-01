package com.tech.jvm.pool;

import java.util.ArrayList;
import java.util.List;

/**
 * -Xms10m -Xmx10m -XX:MetaspaceSize=10m -XX:MaxMetaspaceSize=10m
 * @author lw
 * @since 2022/8/1
 */
public class RuntimeConstantPool {
    public static void main(String[] args) {
        List<String> list=new ArrayList<>();
        for(int i=0;i<1000000000;i++){
            String str = String.valueOf(i).intern();
            list.add(str);
        }
    }
}
