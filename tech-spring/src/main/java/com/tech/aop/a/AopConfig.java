package com.tech.aop.a;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author lw
 * @since 2023-05-17
 */
@EnableAspectJAutoProxy //(exposeProxy = true) // (proxyTargetClass = true)
@ComponentScan
@Configuration
public class AopConfig {
}
