package com.tech.zookeeper.lock.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author lw
 * @since 2024/2/22
 */
@Data
@TableName("t_order")
public class Order {
    
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long pid;
    
    @TableField("user_id")
    private String userId;
}
