package com.tech.atomic;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author lw
 * @since 2023-04-06
 */
@AllArgsConstructor
@Data
public class Node {
    public volatile  int id;
}
