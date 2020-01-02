package com.example.demo.dao;


import com.example.demo.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CourseDao {

    @Autowired
    MongoTemplate mongoTemplate;

    public List<Course> getAllCourses() {
        Query query=new Query();
        List<Course> courses=mongoTemplate.find(query,Course.class,"course");
        return courses;
    }

    public void insertCourse(Course course) {
        mongoTemplate.insert(course,"course");
    }

    public Course getCourseByCid(String cid) {
        Query query=new Query(Criteria.where("cid").is(cid));
        return mongoTemplate.findOne(query,Course.class,"course");
    }

    public void deleteCourse(String cid) {
        Query query=new Query(Criteria.where("cid").is(cid));
        mongoTemplate.remove(query,Course.class,"course");
    }
}
