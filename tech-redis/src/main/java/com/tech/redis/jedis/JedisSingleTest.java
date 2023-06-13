package com.tech.redis.jedis;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Pipeline;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Redis pipeline & lua
 *
 * @author lw
 * @since 2023/6/13
 */
@Slf4j
public class JedisSingleTest {
    public static void main(String[] args) {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        //连接池中最大连接数，在获取连接时，没有可用连接时会被阻塞
        jedisPoolConfig.setMaxTotal(10);
        jedisPoolConfig.setMaxIdle(10);
        jedisPoolConfig.setMinIdle(5);
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, "192.168.50.134", 6379, 30000, null);
        Jedis jedis = null;
        try {
            //从连接池拿一个连接执行命令
            jedis = jedisPool.getResource();
            System.out.println(jedis.set("single", "zhuge"));
            System.out.println(jedis.get("single"));
            //管道示例
            //管道的命令执行方式：cat redis.txt | redis-cli -h 127.0.0.1 -a password -p 6379 --pipe
            Pipeline pipeline = jedis.pipelined();
            for (int i = 0; i < 10; i++) {
                pipeline.incr("pipelineKey");
                pipeline.set("zhuge"+i,"zhuge");
                //一条命令执行失败，不影响后续命令的执行，在结果中会返回异常信息
//                pipeline.incr("zhuge"+i);
            }
            List<Object> results = pipeline.syncAndReturnAll();
            System.out.println(results);
            
            //Lua脚本模拟一个商品减库存的操作
            //Lua脚本命令执行方式：redis-cli --eval /tmp/test.lua
            //初始化商品10016的库存
            jedis.set("product_count_10016","15");
            String script=" local count = redis.call('get',KEYS[1]) " +
                          " local a = tonumber(count) " +
                          " local b = tonumber(ARGV[1]) " +
                          " if a >= b then " +
                          "   redis.call('set',KEYS[1],a-b) " +
                          "   return 1 " +
                          " end " +
//                          " 1===1"+ //模拟Lua脚本报错,脚本中的修改操作会回滚
                          "   return 0";
            Object object = jedis.eval(script, Arrays.asList("product_count_10016"), Arrays.asList("10"));
            System.out.println(object);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //将jedis归还给资源池
            if(jedis!=null){
                jedis.close();
            }
        }
    }


}
