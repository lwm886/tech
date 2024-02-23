package com.cfg.product;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.cloud.zookeeper.serviceregistry.ServiceInstanceRegistration;
import org.springframework.cloud.zookeeper.serviceregistry.ZookeeperRegistration;
import org.springframework.cloud.zookeeper.serviceregistry.ZookeeperServiceRegistry;

import javax.annotation.Resource;

/**
 * @author lw
 * @since 2024/2/23
 */
public class Runner implements ApplicationRunner {
    
    @Resource
    ZookeeperServiceRegistry serviceRegistry;
    
    @Override
    public void run(ApplicationArguments args) throws Exception {
        ZookeeperRegistration zookeeperRegistration = ServiceInstanceRegistration.builder()
                .defaultUriSpec()
                .address("anyUrl")
                .port(10)
                .name("/a/b/c/anotherService")
                .build();
        this.serviceRegistry.register(zookeeperRegistration);
    }
}
