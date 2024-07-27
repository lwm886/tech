package com.tech.sj.basic.test;

import com.tech.sj.basic.entity.SjCourse;
import com.tech.sj.basic.mapper.SjCourseMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author lw
 * @since 2024/7/27
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ShardingJDBCTest {
    
    @Resource
    SjCourseMapper courseMapper;
    
    @Test
    public void addCourse(){
        for (int i = 0; i < 10; i++) {
            SjCourse c = new SjCourse();
            c.setId(Long.valueOf(i));
            c.setName("shardingSphere");
            c.setUserId(Long.valueOf(1000+Long.valueOf(i)));
            c.setStatus("1");
            courseMapper.insert(c);
        }
    }
}
