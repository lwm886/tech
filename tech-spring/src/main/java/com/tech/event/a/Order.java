package com.tech.event.a;

import lombok.Data;

/**
 * @author lw
 * @since 2023-05-15
 */
@Data
public class Order {
    private String id;

    public Order(String id) {
        this.id = id;
    }
}
