package com.tech.redis.jedis;

import redis.clients.jedis.*;

import java.util.HashSet;
import java.util.Set;

/**
 * @author lw
 * @since 2023/6/13
 */
public class JedisSentinelTest {
    public static void main(String[] args) {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(10);
        config.setMaxIdle(10);
        config.setMinIdle(5);
        
        String masterName="mymaster";
        Set<String> sentinels = new HashSet<>();
        //哨兵节点信息
        sentinels.add(new HostAndPort("192.168.50.134",26379).toString());
        sentinels.add(new HostAndPort("192.168.50.134",26380).toString());
        sentinels.add(new HostAndPort("192.168.50.134",26381).toString());
        //JedisSentinelPool其实本质上跟JedisPool类似，都是与Redis主节点建立的连接池
        //JedisSentinelPool并不是说与sentinel建立的连接池，而是通过sentinel发现Redis主节点与其建立连接
        JedisSentinelPool jedisSentinelPool = new JedisSentinelPool(masterName, sentinels, config, 3000, null);
        Jedis jedis=null;   
        try {
            jedis=jedisSentinelPool.getResource();
            System.out.println(jedis.set("sentinel","zhuge"));
            System.out.println(jedis.get("sentinel"));
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(jedis!=null){
                jedis.close();
            }
        }
    }
}
