package com.tech.test.ram.view;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author lw
 * @since 2023/7/28
 */
@Component
public class DataCacheWriter {



    @PostConstruct
    public void init() throws InterruptedException {
        new Thread(()->{
            for(;;){
                long sysTime=System.currentTimeMillis();
                StringBuilder sb=new StringBuilder();
                sb.append(UUID.randomUUID().toString().replace("-",""))
                        .append(",")
                        .append(new Random().nextInt(10))
                        .append(",")
                        .append(new Random().nextDouble())
                        .append(",")
                        .append(new Random().nextDouble())
                        .append(",")
                        .append(new Random().nextDouble())
                        .append(",")
                        .append(new Random().nextDouble())
                        .append(",")
                        .append(new Random().nextDouble())
                        .append(",")
                        .append(new Random().nextDouble())
                        .append(",")
                        .append(new Random().nextDouble())
                        .append(",")
                        .append(new Random().nextDouble())
                        .append(",")
                        .append(new Random().nextDouble())
                        .append(",")
                        .append(new Random().nextDouble())
                        .append(",")
                        .append(new Random().nextDouble())
                        .append(",")
                        .append(new Random().nextDouble())
                        .append(",")
                        .append(new Random().nextDouble())
                        .append(",")
                        .append(new Random().nextDouble())
                        .append(",")
                        .append(new Random().nextDouble())
                        .append(",")
                        .append(new Random().nextDouble())
                        .append(",")
                        .append(new Random().nextDouble())
                        .append(",")
                        .append(new Random().nextDouble())
                        .append(",")
                        .append(new Random().nextDouble())
                        .append(",")
                        .append(new Random().nextDouble())
                        .append(",")
                        .append(new Random().nextDouble())
                        .append(",")
                        .append(new Random().nextDouble())
                        .append(",")
                        .append(new Random().nextDouble())
                        .append(",")
                        .append(new Random().nextDouble())
                        .append(",")
                        .append(new Random().nextDouble())
                        .append(",")
                        .append(new Random().nextDouble())
                        .append(",")
                        .append(sysTime);
                DataCache.queue.addFirst(sb.toString());
                try {
                    while (DataCache.queue.peekLast()!=null && Long.parseLong(DataCache.queue.peekLast().split(",")[DataCache.queue.peekLast().split(",").length-1])<sysTime-60*1000){
                        DataCache.queue.pollLast();
                    }
                } catch (Exception e) {
                    //todo ignore
                }
                try {
                    long wt = System.currentTimeMillis()-sysTime;
                        TimeUnit.MILLISECONDS.sleep(50-wt);
                    
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
