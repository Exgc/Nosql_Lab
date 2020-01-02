package com.example.demo.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.Service.SecretService;
import com.example.demo.Service.StudentService;
import com.example.demo.entity.Student;
import com.example.demo.entity.relation.Secret;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.LinkedList;
import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private SecretService secretService;
    @RequestMapping("/getStudentBySid")
    public Student getStudent(String sid){
        try{
            return studentService.getStudent(sid);
        }catch (Exception e){
            System.out.println(e.toString());
            return null;
        }
    }

//    找出年龄小于20岁的所有学生
    @RequestMapping("/getStudentsUnder20")
    public List<Student> getStudentsUnder20()
    {
        try{
            return studentService.getStudentsUnder20();
        }catch (Exception e){
            System.out.println(e.toString());
            return null;
        }
    }

//    找出年龄小于20岁且是软件学院的学生
    @RequestMapping("/getStudentsUnder20andSoft")
    public List<Student> getStudentUnder20andSoft(){
        try{
            return studentService.getStudentsUnder20andSoft();
        }catch (Exception e){
            System.out.println(e.toString());
            return null;
        }
    }

//    找出学生关系中的所有学生
    @RequestMapping("/getAllStudents")
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }

//    求所有学生的姓名、年龄
    @RequestMapping("/getStudentInfo")
    public List<JSONObject> getStudentInof()
    {
        List<Student> students=studentService.getAllStudents();
        List<JSONObject> jsonObjects=new LinkedList<>();
        for(Student student:students){
            String jsonStr="{\"age\":"+student.getAge()+",\"name\":\""+student.getName()+"\"}";
            System.out.println(jsonStr);
            JSONObject obj=JSON.parseObject(jsonStr);
            jsonObjects.add(obj);
        }
        return jsonObjects;
    }

//    找出年龄小于20岁的学生的姓名、性别
    @RequestMapping("/getStudentInfo2")
    public List<JSONObject> getStudentInof2()
    {
        List<Student> students=studentService.getStudentsUnder20();
        List<JSONObject> jsonObjects=new LinkedList<>();
        for(Student student:students){
            if(student.getAge()==null){
                continue;
            }
            else if (student.getAge()<20){
                String jsonStr="{\"sex\":\""+student.getSex()+"\",\"name\":\""+student.getName()+"\"}";
                JSONObject obj=JSON.parseObject(jsonStr);
                jsonObjects.add(obj);
            }
        }
        return jsonObjects;
    }
    //插入学生
    @RequestMapping("/insertStudent")
    public boolean insertStudent(String sid, String name, String sex, Integer age, String birthday, String dname, String grade){
        try {
            Student student = new Student(sid, name, sex, age, birthday, dname, grade);
            studentService.insertStudent(student);
            return true;
        }catch (Exception e){
            return false;
        }
    }

//    //修改信息
    @RequestMapping("/updateStudent")
    public boolean updateStudent(String sid, String name, String sex, Integer age, String birthday, String dname, String grade){
        try{
            Student student=new Student(sid,name,sex,age,birthday,dname,grade);
            studentService.updateStudent(sid,name,sex,age,birthday,dname,grade);
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    @RequestMapping("/initSecrets")
    public String initSecrets(){
        try{
            List<Student> students=studentService.getAllStudents();
            for(Student student:students){
                Secret secret=new Secret();
                secret.setPassword("123456");
                secret.setSid(student.getSid());

                secretService.insertUser(secret);
                System.out.println(1);
            }
            return "succeed";
        }catch (Exception e){
            return e.toString();
        }
    }
}