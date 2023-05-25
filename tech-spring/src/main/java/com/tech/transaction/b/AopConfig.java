package com.tech.transaction.b;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author lw
 * @since 2023-05-23
 */
@ComponentScan
@Configuration
@EnableTransactionManagement
@EnableAspectJAutoProxy
public class AopConfig {

}
