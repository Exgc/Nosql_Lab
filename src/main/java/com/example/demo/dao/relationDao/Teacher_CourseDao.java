package com.example.demo.dao.relationDao;

import com.example.demo.entity.relation.Teacher_Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Teacher_CourseDao {

    @Autowired
    MongoTemplate mongoTemplate;

    public List<Teacher_Course> getInfoByCid(String cid){
        Query query=new Query(Criteria.where("cid").is(cid));
        return mongoTemplate.find(query, Teacher_Course.class,"teacher_course");
    }
}
