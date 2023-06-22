package com.tech.redis.opt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.Time;
import java.util.concurrent.TimeUnit;

/**
 * @author lw
 * @since 2023/6/20
 */
@Component
public class CacheRebuild {

    @Autowired
    StringRedisTemplate redisTemplate;

    public String get(String key) throws InterruptedException {
        //从redis获取数据
        String cacheData = redisTemplate.opsForValue().get(key);
        //如果数据为空则重建缓存
        if (cacheData == null) {
            //使用分布式锁实现只能让一个线程重建缓存
            Boolean locked = redisTemplate.opsForValue().setIfAbsent("lockKey", Thread.currentThread().getId() + "", 30, TimeUnit.SECONDS);
            if (locked) {
                String dbData = "1";//从数据库查询获取
                redisTemplate.opsForValue().set(key, dbData);
                redisTemplate.delete("lockKey");
                return dbData;
            } else {
                TimeUnit.MILLISECONDS.sleep(50);
                return get(key);
            }
        } else {
            
            return cacheData;
        }
    }
    
    @PostConstruct
    public void init(){
        redisTemplate.delete("k");
        try{
            for(int i=0;i<10;i++){
                int finalI = i;
                new Thread(()->{
                    try {
                        System.out.println(finalI +": getVal="+get("k"));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        }catch (Throwable e){
            e.printStackTrace();
        }
    }
}
