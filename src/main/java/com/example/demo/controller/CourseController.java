package com.example.demo.controller;


import com.example.demo.Service.CourseService;
import com.example.demo.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;

//    检索所有课程情况
    @RequestMapping("/getAllCourses")
    public List<Course> getAllCourses(){
        return courseService.getAllCourses();
    }

//    检索先行课号为“300001”的课程名
    @RequestMapping("/getCourseName")
    public String getCourseNameByCid(String cid){
        System.out.println(cid);
        List<Course> courses=courseService.getAllCourses();
        for(Course course:courses){
            if(course.getCid().equals(cid)){
                return course.getName();
            }
        }
        return null;
    }

    //插入课程
    @RequestMapping("/insertCourse")
    public String insertCourse(String cid,String fcid,String name,Integer credit){
        try{
            Course course=new Course(cid, fcid, name, credit);
            courseService.insertCourse(course);
        }catch (Exception e){
            return e.toString();
        }
        return "success";
    }

    //删除课程
    @RequestMapping("/deleteCourse")
    public boolean deleteCourse(String cid){
        try {
            courseService.deleteCourse(cid);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
