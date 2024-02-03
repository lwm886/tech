package com.tech.test.ram.controller;

import com.google.common.collect.Lists;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author lw
 * @since 2023/6/25
 */
@Slf4j
@RestController
public class UserController {
    
    @Autowired
    JdbcTemplate jdbcTemplate;
    
    @GetMapping("query")
    public List query() throws InterruptedException {
        String sql="select * from sys_user";
        List<Map<String, Object>> res = jdbcTemplate.queryForList(sql);
        TimeUnit.MILLISECONDS.sleep(50);
        return res;
    }
    
    @GetMapping("save")
    public String save() throws InterruptedException {
//        log.info("save ...");
        String sql="INSERT INTO sys_user(name, email, age, sex, account, pwd, company, address, phone) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        for (int i = 0; i < 1000000; i++) {
            jdbcTemplate.update(sql,"zhuge"+System.currentTimeMillis(),"zhuge@163.com",100,1, UUID.randomUUID().toString(),"123456","aac","北京","1001000");

        }
//        System.out.println("save ok"    );
        return "ok";
    }

    @GetMapping("tc")
    public List tc() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(500);
        System.out.println("ok");
        return Lists.newArrayList("ok");
    }
}
