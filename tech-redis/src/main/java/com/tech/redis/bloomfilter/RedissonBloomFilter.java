package com.tech.redis.bloomfilter;

import org.redisson.Redisson;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 布隆过滤器认为元素不存在，那么一定不存在，隆过滤器认为元素存在，可能不存在
 * 布隆过滤里面的key如下需要修改，那么需要重新初始化一个布隆过滤器
 * @author lw
 * @since 2023/6/23
 */
public class RedissonBloomFilter {
    public static void main(String[] args) {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://192.168.50.134 :6379");
        //构造Redisson
        RedissonClient redissonClient = Redisson.create(config);

        RBloomFilter<Object> bloomFilter = redissonClient.getBloomFilter("nameList");
        //初始化布隆过滤器：预计元素数量为100000，误差为3%，根据这两个参数会计算出合适的底层bit数组大小
        bloomFilter.tryInit(100000L,0.03);
        //将zhuge添加到布隆过滤器
        bloomFilter.add("zhuge");
        
        //判断下面的名称是否在布隆过滤器
        System.out.println(bloomFilter.contains("guojia"));
        System.out.println(bloomFilter.contains("baiqi"));
        System.out.println(bloomFilter.contains("zhuge"));
    }
    
    //布隆过滤器防止缓存穿透,服务启动时，初始化所有key到布隆过滤器
    public void init(){
        Config config = new Config();
        config.useSingleServer().setAddress("redis://192.168.50.134:6379");
        //构造Redisson
        RedissonClient redissonClient = Redisson.create(config);

        RBloomFilter<Object> bloomFilter = redissonClient.getBloomFilter("nameList");
        //初始化布隆过滤器：预计元素数量为100000，误差为3%，根据这两个参数会计算出合适的底层bit数组大小
        bloomFilter.tryInit(100000L,0.03);
        
        //f服务启动时，把所有key添加到布隆过滤器
        List<String> keys=new ArrayList<>();//提前查询出所有key
        for (String k: keys) {
            bloomFilter.add(k);
        }
    }

    /**
     * 查询数据
     * 先过布隆过滤器，如果布隆过滤器判断不存在，则一定不存在，那么直接返回
     * 如果布隆过滤器判断存在，那么可能不存在，先查缓存，如果缓存为空，再查数据库，缓存查询结果，如果数据库返回空，那么就缓存空标志，并设置过期时间。如果缓存不为空直接返回。
     * @param bloomFilter
     * @param redisTemplate
     * @param key
     * @return
     */
    String get(RBloomFilter<Object> bloomFilter, StringRedisTemplate redisTemplate, String key){
        //从布隆过滤器获取key是否存在
        boolean exit = bloomFilter.contains(key);
        if(!exit){
            return "";
        }
        String s = redisTemplate.opsForValue().get(key);
        if(StringUtils.isEmpty(s)){
            String v="10" ; //查询数据库
            s=v;
            redisTemplate.opsForValue().set(key,v==null?"null":v);
            //如果存储数据为空，需要设置一个过期时间
            if(v==null){
                redisTemplate.expire(key,30, TimeUnit.SECONDS);
            }
        }
        return s;
    }
}
