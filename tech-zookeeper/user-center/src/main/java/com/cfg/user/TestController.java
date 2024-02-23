package com.cfg.user;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author lw
 * @since 2024/2/23
 */
@RestController
public class TestController {
    
    @Resource
    RestTemplate restTemplate;
    
    @Resource
    LoadBalancerClient loadBalancerClient;
    
    @GetMapping("/test")
    public String test(){
        return this.restTemplate.getForObject("http://product-center/getInfo",String.class);
    }
    @GetMapping("/lb")
    public String getLb(){
        ServiceInstance choose = loadBalancerClient.choose("product-center");
        String serviceId= choose.getServiceId();
        int port = choose.getPort();
        return serviceId+" "+port;
    }
}
