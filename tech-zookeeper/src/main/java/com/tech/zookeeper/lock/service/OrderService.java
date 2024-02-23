package com.tech.zookeeper.lock.service;

import com.tech.zookeeper.lock.entity.Order;
import com.tech.zookeeper.lock.entity.Product;
import com.tech.zookeeper.lock.mapper.OrderMapper;
import com.tech.zookeeper.lock.mapper.ProductMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author lw
 * @since 2024/2/22
 */
@Service
public class OrderService {
    
    @Resource
    OrderMapper orderMapper;
    
    @Resource
    ProductMapper productMapper;
    
    @Transactional
    public void reduceStock(long id){
        Product product = productMapper.selectById(id);
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        
        if(product.getStock()<=0){
            throw new RuntimeException("OUT OF STOCK");
        }
        
        int c = productMapper.decrStock(id);
        if(c==1){
            Order order = new Order();
            order.setUserId(UUID.randomUUID().toString());
            orderMapper.insert(order);
        }else{
            throw new RuntimeException("reduce stock fail");
        }

    }    
}
