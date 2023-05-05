package com.tech.ioc.g;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * @author lw
 * @since 2023-05-04
 */
@Data
public class Tank {
    private String name="default";
}
