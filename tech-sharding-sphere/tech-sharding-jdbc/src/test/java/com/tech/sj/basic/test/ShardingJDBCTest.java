package com.tech.sj.basic.test;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tech.sj.basic.entity.Course;
import com.tech.sj.basic.entity.SjCourse;
import com.tech.sj.basic.mapper.CourseMapper;
import com.tech.sj.basic.mapper.SjCourseMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.xml.bind.annotation.W3CDomHandler;
import java.util.List;

/**
 * @author lw
 * @since 2024/7/27
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ShardingJDBCTest {
    
    @Resource
    CourseMapper courseMapper;
    
    @Test
    public void addCourse(){
        for (int i = 0; i < 10; i++) {
            Course c = new Course();
//            c.setCid(Long.valueOf(i));
            c.setCname("shardingSphere");
            c.setUserId(Long.valueOf(1000+Long.valueOf(i)));
            c.setCstatus("1");
            courseMapper.insert(c);
        }
    }

    @Test
    public void queryCourse(){
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("cid");
        wrapper.eq("cid",1L); //根据分片键直接定位库和表
        List<Course> courses = courseMapper.selectList(wrapper);
        courses.forEach(System.out::println);
    }
    
}
