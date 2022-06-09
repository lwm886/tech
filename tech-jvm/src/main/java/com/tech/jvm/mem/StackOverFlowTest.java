package com.tech.jvm.mem;

/**
 * @Author lw
 * @Date 2022/5/30
 * @Description -Xss 128k,默认1M
 */
public class StackOverFlowTest {
    static int count;
    static void redo(){
        count++;
        redo();
    }

    public static void main(String[] args) {
        try{
            redo();
        }catch (Throwable e){
            e.printStackTrace();
            System.out.println(count);
        }
    }
}
