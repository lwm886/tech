package com.tech.zookeeper.lock.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tech.zookeeper.lock.entity.Order;
import com.tech.zookeeper.lock.entity.Product;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}
