package com.tech.transaction.c;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author lw
 * @since 2023-05-25
 */
@EnableTransactionManagement
@EnableAspectJAutoProxy
@ComponentScan
@Configuration
public class AopConfig {
    @Bean
    public JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate(getDataSource());
    }

    @Bean("dataSource")
    public DataSource getDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://10.0.4.3:3306/rdp_data?allowMultiQueries=true&useSSL=false&useUnicode=true&characterEncoding=UTF-8");
        dataSource.setUsername("rdp_data_rw");
        dataSource.setPassword("5aG7vLEzg5AlvjOD");
        return dataSource;
    }

    @Bean("transactionManager")
    public PlatformTransactionManager txManager(@Qualifier("dataSource") DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }
}
