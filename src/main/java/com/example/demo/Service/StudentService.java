package com.example.demo.Service;


import com.example.demo.dao.StudentDao;
import com.example.demo.entity.Student;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentDao studentDao;

    public Student getStudent(String sid) {
        try{
            return studentDao.getStudent(sid);
        }catch (Exception e){
            return null;
        }
    }

    public void insertStudent(Student student) {
        studentDao.insertStudent(student);
    }

    public List<Student> getStudentsUnder20() {
        return studentDao.getStudentsUnderAge20();
    }

    public List<Student> getStudentsUnder20andSoft() {
        return studentDao.getStudentsUnderAge20andSoft();
    }

    public List<Student> getAllStudents() {
        return studentDao.getAllStudents();
    }

    public void updateStudent(String sid, String name, String sex, Integer age,String birthday, String dname, String grade) {
        studentDao.updateStudent(sid,name,sex,age,birthday,dname,grade);
    }

    public void getname() {
        studentDao.getname();
    }

}
