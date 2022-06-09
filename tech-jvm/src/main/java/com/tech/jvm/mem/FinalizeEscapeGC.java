package com.tech.jvm.mem;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author lw
 * @Date 2022/6/8
 * @Description Finalize 回收救赎
 */
@Slf4j
public class FinalizeEscapeGC {

    public static FinalizeEscapeGC SAVE_HOOK=null;

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        log.info("执行了finalize");
        SAVE_HOOK=this;
    }

    public static void main(String[] args) throws InterruptedException {
        SAVE_HOOK=new FinalizeEscapeGC();
        SAVE_HOOK=null;
        System.gc();
        Thread.sleep(500);
        if(SAVE_HOOK != null){
            log.info("存在");
        }else{
            log.info("不存在");
        }

        SAVE_HOOK=null;
        System.gc();
        Thread.sleep(500);
        if(SAVE_HOOK != null){
            log.info("存在");
        }else{
            log.info("不存在");
        }
    }
}
