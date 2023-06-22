package com.tech.redis.connpool;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lw
 * @since 2023/6/22
 */
public class ConnPoolSetter {

    List<Jedis> minIdleJedisList = new ArrayList<>();

    JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();

    /**
     * 系统启动就有大量请求访问redis，需要预热连接池，通常预热连接数为minIdle
     * 预热Redis连接池
     */
    public void preheat() {

        jedisPoolConfig.setMaxTotal(10);
        jedisPoolConfig.setMaxIdle(10);
        jedisPoolConfig.setMinIdle(5);
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, "192.168.50.134", 6379, 30000, null);

            for (int i = 0; i < jedisPoolConfig.getMinIdle(); i++) {
                Jedis jedis = null;

                try {
                    jedis = jedisPool.getResource();
                    minIdleJedisList.add(jedis);
                    jedis.ping();
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    //注意，这里不能马上close，将连接还回连接池，否则预热的就只有一个连接
                }
            }
        
        //统一将预热的连接还回连接池
        for (int i = 0; i < jedisPoolConfig.getMinIdle(); i++) {
            Jedis jedis=null;
            try{
                jedis = minIdleJedisList.get(i);
                //将连接还回连接池
                jedis.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
