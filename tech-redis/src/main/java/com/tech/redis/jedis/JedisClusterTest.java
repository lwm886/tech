package com.tech.redis.jedis;

import redis.clients.jedis.*;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * RedisCluster 集群
 * @author lw
 * @since 2023/6/14
 */
public class JedisClusterTest {
    public static void main(String[] args) throws IOException {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(10);
        config.setMaxIdle(10);
        config.setMinIdle(5);

        Set<HostAndPort> jedisClusterNode = new HashSet<>();
        jedisClusterNode.add(new HostAndPort("192.168.50.134",6371));
        jedisClusterNode.add(new HostAndPort("192.168.50.134",6372));
        jedisClusterNode.add(new HostAndPort("192.168.50.134",6373));
        jedisClusterNode.add(new HostAndPort("192.168.50.134",6374));
        jedisClusterNode.add(new HostAndPort("192.168.50.134",6375));
        jedisClusterNode.add(new HostAndPort("192.168.50.134",6376));
      
        JedisCluster jedisCluster=null;
        try {
            jedisCluster=new JedisCluster(jedisClusterNode,6000,5000,10,config);
            System.out.println(jedisCluster.set("cluster","zhuge"));
            System.out.println(jedisCluster.get("cluster"));
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(jedisCluster!=null){
                jedisCluster.close();
            }
        }
    }
}
