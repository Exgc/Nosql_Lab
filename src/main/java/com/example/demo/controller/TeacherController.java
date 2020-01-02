package com.example.demo.controller;

import com.example.demo.Service.TeacherService;
import com.example.demo.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

@RestController
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @RequestMapping("/getTeacherByTid")
    public Teacher getTeacherByTid(String tid){
        return teacherService.getTeacherByTid(tid);
    }

    @RequestMapping("/getAllTeachers")
    public List<Teacher> getAllTeachers(){
        return teacherService.getAllTeachers();
    }

//    找出年龄大于50岁的老师
    @RequestMapping("/getTeacherAboveAge")
    public List<Teacher> getTeacherAboveAge(int age){
        List<Teacher> teachers=teacherService.getAllTeachers();
        List<Teacher> res_teachers=new LinkedList<>();
        for(Teacher teacher:teachers){
            if(teacher.getAge()==null){
                continue;
            }else if(teacher.getAge()>age){
                res_teachers.add(teacher);
            }
        }
        return res_teachers;
    }

//    找出所有的男老师
    @RequestMapping("/getTeacherBySex")
    public List<Teacher> getTeacherBySex(String sex)throws NullPointerException{
        List<Teacher> teachers=teacherService.getAllTeachers();
        List<Teacher> res_teachers=new LinkedList<>();
        for(Teacher teacher:teachers){
            if (teacher.getSex()==null){
                continue;
            }
            else if(teacher.getSex().equals(sex)){
                res_teachers.add(teacher);
            }
        }
        return res_teachers;
    }
//    找出所有在CS学院的老师
    @RequestMapping("/getTeacherBydname")
    public List<Teacher> getTeacherBydname(String dname)throws NullPointerException{
        List<Teacher> teachers=teacherService.getAllTeachers();
        List<Teacher> res_teachers=new LinkedList<>();
        for (Teacher teacher:teachers){
            if (teacher.getDname()==null){
                continue;
            }
            else if(teacher.getDname().equals(dname)){
                res_teachers.add(teacher);
            }
        }
        return res_teachers;
    }

    //插入老师
    @RequestMapping("/insertTeacher")
    public void insertTeacher(String tid, String name, String sex, String dname, Integer age){
        Teacher teacher=new Teacher(tid, name, sex, dname, age);
        teacherService.insertTeacher(teacher);
    }
}
