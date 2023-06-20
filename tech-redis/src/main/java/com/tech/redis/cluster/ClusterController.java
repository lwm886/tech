package com.tech.redis.cluster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lw
 * @since 2023/6/14
 */
@RestController
public class ClusterController {

    @Autowired
    StringRedisTemplate stringRedisTemplate;
    
    @GetMapping("c")
    public void test(){
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
