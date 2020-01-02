package com.example.demo.Service;


import com.example.demo.dao.TeacherDao;
import com.example.demo.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    @Autowired
    private TeacherDao teacherDao;

    public List<Teacher> getAllTeachers() {
        return teacherDao.getAllTeachers();
    }

    public void insertTeacher(Teacher teacher) {
        teacherDao.insertTeacher(teacher);
    }

    public Teacher getTeacherByTid(String tid){
        return teacherDao.getTeacherByTid(tid);
    }
}
