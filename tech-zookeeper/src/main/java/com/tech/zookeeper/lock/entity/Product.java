package com.tech.zookeeper.lock.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author lw
 * @since 2024/2/22
 */
@Data
@TableName("t_product")
public class Product {
    private Long id;
    
    @TableField("product_name")
    private String productName;
    
    private long stock;
    
    private long version;
}
