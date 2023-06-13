package com.tech.redis.sentinel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lw
 * @since 2023/6/13
 */
@RestController
public class SentinelController {
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    
    //测试主节点挂掉，哨兵重新选举主节点，客户端能否动态感知到
    //新的master节点选出后，哨兵会把消息发布出去，客户端实际实现了消息监听机制
    //哨兵把新的master信息发布出去，客户端会立刻感知到新的master信息，动态切换访问的master ip
    @RequestMapping("testSentinel")
    public void testSentinel() throws InterruptedException {
        int i=1;
        while (true){
            try {
                stringRedisTemplate.opsForValue().set("zhuge"+i,i+"");
                System.out.println("设置key: "+"zhuge"+i);
                i++;
                Thread.sleep(1000);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }
}
