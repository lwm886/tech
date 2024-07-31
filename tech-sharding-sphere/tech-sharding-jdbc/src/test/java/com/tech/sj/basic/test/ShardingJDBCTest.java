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

    //inline分配策略不支持多分片键、范围查询的场景,支持等于或者in等精确值查找
    @Test
    public void queryCourse(){
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("cid");
//        wrapper.eq("cid",1L); //根据分片键直接定位库和表
        wrapper.in("cid",1024781933592186881L,1L);
        List<Course> courses = courseMapper.selectList(wrapper);
        courses.forEach(System.out::println);
    }
    
    //inline分片策略不支持范围查找，例如between and，使用standard分片策略测试
    @Test
    public void queryCourseRange(){
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        wrapper.between("cid",1024781933592186881L,1024781933634129921L);
        List<Course> courses = courseMapper.selectList(wrapper);
        courses.forEach(System.out::println);
    }

    //complex分片策略  支持多分片键复杂查询
    //如果使用standard分片策略此时会到各个库上的各个表上查询，其实根据user_id就能够定位到表，理想是到一个表上去查，这时候适合使用complex分片策略
    @Test
    public void queryCourseComplex(){
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        wrapper.between("cid",1024781933592186881L,1024781933634129921L);
        wrapper.eq("user_id",1001L);
        List<Course> courses = courseMapper.selectList(wrapper);
        courses.forEach(System.out::println);
    }
}
