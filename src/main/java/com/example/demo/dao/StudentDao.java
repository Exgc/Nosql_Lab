package com.example.demo.dao;


import com.example.demo.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

@Component
public class StudentDao {


    @Autowired
    MongoTemplate mongoTemplate;

    public Student getStudent(String sid) {
        Query query=new Query(Criteria.where("sid").is(sid));
        Student student =  mongoTemplate.findOne(query,Student.class,"student");
        System.out.println(student.toString());
        return student;
    }

    public List<Student> getStudentsUnderAge20(){
        Query query=new Query(Criteria.where("age").lt(20));
        List<Student> students=mongoTemplate.find(query,Student.class,"student");
        return students;
    }

    public List<Student> getStudentsUnderAge20andSoft() {
        Query query=new Query(Criteria.where("age").lt(20).and("dname").is("软件学院"));
        List<Student> students=mongoTemplate.find(query,Student.class,"student");
        return students;
    }

    public List<Student> getAllStudents() {
        Query query=new Query();
        List<Student> students=mongoTemplate.find(query,Student.class,"student");
        return students;
    }

    public void insertStudent(Student student) {
        mongoTemplate.save(student,"student");
    }

    public void updateStudent(String sid, String name, String sex, Integer age, String birthday, String dname, String grade) {
        Query query=new Query();
        query.addCriteria(Criteria.where("sid").is(sid));
        Update update=new Update();
        System.out.println(sid+" "+name+" "+sex+" "+age+" "+birthday+" "+dname+" "+grade);
        if(!(name==null)){
            update.set("name",name);
        }
        if(!(sex==null)){
            update.set("sex",sex);
        }
        if(!(age==null)){
            System.out.println(age);
            update.set("age",age);
        }
        if(!(birthday==null)){
            update.set("birthday",birthday);
        }
        if(!(dname==null)){
            update.set("dname",dname);
        }
        if(!(grade==null)){
            update.set("grade",grade);
        }
        mongoTemplate.updateFirst(query,update,"student");
    }

    public void getname() {
        Query query=new Query(Criteria.where("dname").is("耿少杰"));
        Student student=mongoTemplate.findOne(query,Student.class,"Student");
    }
}
