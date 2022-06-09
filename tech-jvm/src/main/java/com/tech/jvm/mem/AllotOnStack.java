package com.tech.jvm.mem;

/**
 * @Author lw
 * @Date 2022/6/7
 * @Description 逃逸分析 标量替换
 * 代码调用了1亿次alloc()，如果是分配到堆上，大概需要1GB以上堆空间，如果堆空间小于该值，必然会触发GC。
 *
 * 使用如下参数不会发生GC 逃逸分析 标量替换默认已经开启
 * -Xmx15m -Xms15m -XX:+DoEscapeAnalysis -XX:+PrintGC -XX:+EliminateAllocations
 *
 * 使用如下参数都会发生大量GC
 *
 * 关闭逃逸分析 标量替换也失效
 * -Xmx15m -Xms15m -XX:-DoEscapeAnalysis -XX:+PrintGC -XX:+EliminateAllocations
 *
 * 开启逃逸分析 关闭标量替换，栈上没有足够大的内存给对象使用，对象还是在堆上分配内存 有大量GC
 * -Xmx15m -Xms15m -XX:+DoEscapeAnalysis -XX:+PrintGC -XX:-EliminateAllocations
 *
 */
public class AllotOnStack {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for(int i=0;i<100000000;i++){
            alloc();
        }
        long end=System.currentTimeMillis();
        System.out.println(end-start);
    }

    private static void alloc() {
        Users users = new Users();
        users.setUserId(1);
        users.setName("zg");
    }


}
