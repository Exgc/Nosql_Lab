package com.example.demo.Service;

import com.example.demo.dao.CourseDao;
import com.example.demo.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CourseService {

    @Autowired
    private CourseDao courseDao;


    public List<Course> getAllCourses() {
        return courseDao.getAllCourses();
    }

    public void insertCourse(Course course) {
        courseDao.insertCourse(course);
    }

    public Course getCourseByCid(String cid){
        return courseDao.getCourseByCid(cid);
    }

    public void deleteCourse(String cid) {
        courseDao.deleteCourse(cid);
    }
}
