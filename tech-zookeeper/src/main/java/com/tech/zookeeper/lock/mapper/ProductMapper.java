package com.tech.zookeeper.lock.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tech.zookeeper.lock.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ProductMapper extends BaseMapper<Product> {

    @Update(" update t_product set stock=stock-1    where id=#{id}  ")
    int decrStock(@Param("id") Long id);
}
