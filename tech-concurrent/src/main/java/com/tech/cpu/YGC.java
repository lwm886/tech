package com.tech.cpu;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lw
 * @since 2023-01-05
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError
 * 通过TOP观测，发现GC线程确实占用CPU比较多
 */
public class YGC {
    public static void main(String[] args) throws InterruptedException {

        Thread.currentThread().setName("THREAD-MAIN");
        long k = 0;
        while (true) {
            List<UserData> list = queryDB();
            List<UserVo> resList = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                UserData userData = list.get(i);
                UserVo vo = getVo(userData);
                resList.add(vo);
            }
            k=k+resList.size();
            list.clear();
            resList.clear();
            Thread.sleep(10);
            if (k > 10000) {
                System.out.println(k);
                k = 0;
            }
        }
    }

    public static UserVo getVo(UserData userData) {
        UserVo uv = new UserVo();
        byte[] bytes = userData.getBytes();
        uv.setBytes(bytes);
        return uv;
    }


    private static List<UserData> queryDB() {
        List<UserData> resList = new ArrayList<>();
        resList.add(new UserData());
        resList.add(new UserData());
        resList.add(new UserData());
        resList.add(new UserData());
        resList.add(new UserData());

        return resList;
    }
}
