package com.tech.sj.basic.controller;


import com.alibaba.fastjson.JSON;
import com.tech.sj.basic.entity.SjCourse;
import com.tech.sj.basic.service.ISjCourseService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lw
 * @since 2024-07-27
 */
@RestController
@RequestMapping("/basic/course")
public class CourseController {

    @Resource
    ISjCourseService courseService;

    @RequestMapping("/detail")
    public String detail(String id){
        SjCourse courseServiceById = courseService.getById(id);
        return JSON.toJSONString(courseServiceById);
    }
}
