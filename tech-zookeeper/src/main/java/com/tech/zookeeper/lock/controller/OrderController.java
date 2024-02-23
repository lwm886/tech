package com.tech.zookeeper.lock.controller;

import com.tech.zookeeper.lock.service.OrderService;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMultiLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author lw
 * @since 2024/2/22
 */
@RestController
public class OrderController {

    @Resource
    OrderService orderService;

    @Resource
    CuratorFramework curatorFramework;

    @Value("${server.port}")
    String port;

    @GetMapping("stock")
    public String reduceStock(long id) throws Exception {
        InterProcessMutex mutex = new InterProcessMutex(curatorFramework, "/product_" + id);
        try {
            mutex.acquire();
            orderService.reduceStock(id);
        } catch (Exception e) {
            if(e instanceof RuntimeException){
                throw e;
            }
        } finally {
            mutex.release();
        }
        return "ok : " + port;
    }
}
