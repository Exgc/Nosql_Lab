package com.example.demo.dao;

import com.example.demo.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TeacherDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Teacher> getAllTeachers() {
        Query query=new Query();
        List<Teacher> teachers=mongoTemplate.find(query,Teacher.class,"teacher");
        return teachers;
    }

    public void insertTeacher(Teacher teacher) {
        mongoTemplate.save(teacher,"teacher");
    }

    public Teacher getTeacherByTid(String tid) {
        Query query=new Query(Criteria.where("tid").is(tid));
        return mongoTemplate.findOne(query,Teacher.class,"teacher");
    }
}
