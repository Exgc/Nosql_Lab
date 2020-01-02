package com.example.demo.controller.relation_Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.Service.CourseService;
import com.example.demo.Service.StudentService;
import com.example.demo.Service.TeacherService;
import com.example.demo.Service.relationService.Student_CourseService;
import com.example.demo.Service.relationService.Teacher_CourseService;
import com.example.demo.entity.Course;
import com.example.demo.entity.Student;
import com.example.demo.entity.Teacher;
import com.example.demo.entity.relation.ClassInfo;
import com.example.demo.entity.relation.Student_Course;
import com.example.demo.entity.relation.Teacher_Course;
import com.fasterxml.jackson.annotation.JsonAlias;
import jdk.nashorn.internal.runtime.ListAdapter;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RestController
public class Student_CourseController {
    @Autowired
    private Student_CourseService student_courseService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private Teacher_CourseService teacher_courseService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @RequestMapping("/getInfoBySid")
    public List<Student_Course> getInfoBySid(String sid){
        return student_courseService.getInfoBySid(sid);
    }

    @RequestMapping("deleteInfo")
    public boolean deleteInfo(String sid,String cid,String tid){
        try{
            student_courseService.deleteInfo(sid,cid,tid);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @RequestMapping("/deleteInfoBySid")
    public boolean deleteInfoBySid(String sid){
        try{
            student_courseService.deleteInfoBySid(sid);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @RequestMapping("/insertInfo")
    public boolean insertInfo(String sid,String cid,String tid){
        try{
            Student_Course student_course=new Student_Course(sid,cid,tid,null);
            student_courseService.insertInfo(student_course);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @RequestMapping("/updateInfo")
    public boolean updateInfo(String sid,String cid,String tid,Integer score){
        try{
            Student_Course student_course=new Student_Course(sid,cid,tid,score);
            student_courseService.updateInfo(student_course);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @RequestMapping("/getClassesBySid")
    public List<ClassInfo> getClassesBySid(String sid){
        try{
            List<Student_Course> student_courses=student_courseService.getCoursesBySid(sid);
            List<Course> courses=new ArrayList<>();
            List<ClassInfo> classInfos=new ArrayList<>();
            for (Student_Course student_course:student_courses){
                System.out.println(1+"   "+student_course.getCid());
                Course course=courseService.getCourseByCid(student_course.getCid());
                Teacher teacher=teacherService.getTeacherByTid(student_course.getTid());
                if(course==null||teacher==null)continue;
                ClassInfo classInfo=new ClassInfo(student_course.getCid(),teacher.getName(),teacher.getTid(),course.getFcid(),course.getCredit(),course.getName());
                classInfos.add(classInfo);
            }
            return classInfos;
        }catch (Exception e){
            System.out.println(e.toString());
            return null;
        }
    }

    @RequestMapping("/getNotClassesBySid")
    public List<ClassInfo> getNotClassesBySid(String sid){
        try{
            List<Student_Course> student_courses=student_courseService.getCoursesBySid(sid);
            List<String> CidList=new ArrayList<>();
            for (Student_Course student_course:student_courses){
                Course course=courseService.getCourseByCid(student_course.getCid());
                if(course==null)continue;
                CidList.add(course.getCid());
            }
            List<Course> Allcourses=courseService.getAllCourses();
            List<ClassInfo> classInfos=new ArrayList<>();

            //if a cid not in CidList,put it into the classInfos with its teacher
            for(Course course:Allcourses){
                if(!CidList.contains(course.getCid())){
                    System.out.println(course.getCid()+"     "+course.getName()+"        ");
                    List<Teacher_Course> teacher_courses=teacher_courseService.getInfoByCid(course.getCid());
                    if(teacher_courses==null)continue;
                    for(Teacher_Course teacher_course:teacher_courses){
                        System.out.println(course.getCid()+"      "+teacher_course.getTid());
                        Teacher teacher=teacherService.getTeacherByTid(teacher_course.getTid());
                        if(teacher==null)continue;
                        System.out.println(teacher.getTid()+"         "+teacher.getName());
                        ClassInfo classInfo=new ClassInfo(course.getCid(),teacher.getName(),teacher.getTid(),course.getFcid(),course.getCredit(),course.getName());
                        classInfos.add(classInfo);
                    }
                }
            }
            return classInfos;
        }catch (Exception e){
            System.out.println(e.toString());
            return null;
        }
    }

    @RequestMapping("/getDistinctCourse")
    public List<Course> getDistinctCourse(){
        try{
            List<String> CidList=student_courseService.getDistinctCourse();
            List<Course> courses=new ArrayList<>();
            for(String cid:CidList){
                Course course=courseService.getCourseByCid(cid);
                courses.add(course);
            }
            return courses;
        }catch (Exception e){
            return null;
        }
    }

    //找出平均成绩排名前10的学生
    @RequestMapping("/getAvgScore")
    public List<JSONObject> getAvgScore(){
        try{
            List<Document> documents=student_courseService.getAvgScore();
            List<JSONObject> jsonObjects=new LinkedList<>();
            for(Document document:documents){
                Student student=studentService.getStudent(document.get("sid").toString());
                String jsonStr="{\"sid\":"+document.get("sid")+",\"name\":\""+student.getName()+"\",\"avg_score\":\""+document.get("avg_score")+"\"}";
                System.out.println(jsonStr);
                JSONObject obj= JSON.parseObject(jsonStr);
                jsonObjects.add(obj);
            }
            return jsonObjects;
        }catch (Exception e){
            System.out.println(e.toString());
            return null;
        }
    }

    //找出平均成绩排名前10的课程
    @RequestMapping("/getCourseAvgScore")
    public List<JSONObject> getCourseAvgScore(){
        try{
            List<Document> documents=student_courseService.getCourseAvgScore();
            List<JSONObject> jsonObjects=new LinkedList<>();
            for(Document document:documents){
                System.out.println(document.get("cid"));
                Course course=courseService.getCourseByCid(document.get("cid").toString());
                String jsonStr="{\"cid\":"+document.get("cid")+",\"name\":\""+course.getName()+"\",\"avg_score\":\""+document.get("avg_score")+"\"}";
                System.out.println(jsonStr);
                JSONObject obj= JSON.parseObject(jsonStr);
                jsonObjects.add(obj);
            }
            return jsonObjects;
        }catch (Exception e){
            System.out.println(e.toString());
            return null;
        }
    }

    //求选课人数排名前10的课程
    @RequestMapping("/getFirst10Course")
    public List<JSONObject> getFrist10Course(){
        try{
            List<Document> documents=student_courseService.getFirst10Course();
            List<JSONObject> jsonObjects=new LinkedList<>();
            for (Document document:documents){
                Course course=courseService.getCourseByCid(document.get("cid").toString());
                String jsonStr="{\"cid\":"+document.get("cid")+",\"name\":\""+course.getName()+"\",\"count_student\":\""+document.get("count_student")+"\"}";
                System.out.println(jsonStr);
                JSONObject obj= JSON.parseObject(jsonStr);
                jsonObjects.add(obj);
            }
            return jsonObjects;
        }catch (Exception e){
            return null;
        }
    }

    //找出选课数目排名前10的学生
    @RequestMapping("/getFirst10Student")
    public List<JSONObject> getFrist10Student(){
        try{
            List<Document> documents=student_courseService.getFirst10Student();
            List<JSONObject> jsonObjects=new LinkedList<>();
            for (Document document:documents){
                Student student=studentService.getStudent(document.get("sid").toString());
                String jsonStr="{\"sid\":"+document.get("sid")+",\"name\":\""+student.getName()+"\",\"count_course\":\""+document.get("count_course")+"\"}";
                System.out.println(jsonStr);
                JSONObject obj= JSON.parseObject(jsonStr);
                jsonObjects.add(obj);
            }
            return jsonObjects;
        }catch (Exception e){
            return null;
        }
    }


    //找出每位同学的最高成绩以及最高成绩对应的课程名
    @RequestMapping("/getBestClassForStudent")
    public List<JSONObject> getBestClassForStudent(){
        try {
            List<Document> documents=student_courseService.getBestClassForStdent();
            List<JSONObject> jsonObjects=new LinkedList<>();
            for(Document document:documents){
                Student student=studentService.getStudent(document.get("sid").toString());
                Course course=courseService.getCourseByCid(document.get("cid").toString());
                if(student==null||course==null||document==null)continue;
                String jsonStr="{\"sname\":\""+student.getName()+"\",\"cname\":\""+course.getName()+"\",\"max_score\":\""+document.get("max_score")+"\"}";
                System.out.println(student.getSid()+"----"+jsonStr);
                JSONObject obj= JSON.parseObject(jsonStr);
                jsonObjects.add(obj);
            }
            return jsonObjects;
        }catch (Exception e){
            System.out.println(e.toString());
            return null;
        }
    }

    //求每门课程最高成绩以及最高成绩对应的学生姓名
    @RequestMapping("/getBestStudentForCourse")
    public List<JSONObject> getBestStudentForCourse(){
        try {
            List<Document> documents=student_courseService.getBestStudentForCourse();
            List<JSONObject> jsonObjects=new LinkedList<>();
            for(Document document:documents){
                Student student=studentService.getStudent(document.get("sid").toString());
                Course course=courseService.getCourseByCid(document.get("cid").toString());
                if(student==null||course==null||document==null)continue;
                String jsonStr="{\"sname\":\""+student.getName()+"\",\"cname\":\""+course.getName()+"\",\"max_score\":\""+document.get("max_score")+"\"}";
                System.out.println(student.getSid()+"----"+jsonStr);
                JSONObject obj= JSON.parseObject(jsonStr);
                jsonObjects.add(obj);
            }
            return jsonObjects;
        }catch (Exception e){
            System.out.println(e.toString());
            return null;
        }
    }

    @RequestMapping("/getInfoForCourse")
    public List<JSONObject> getInfoForCourse(){
        try {
            List<Document> documents=student_courseService.getInfoForCourse();
            List<JSONObject> jsonObjects=new LinkedList<>();
            for(Document document:documents){
                Course course=courseService.getCourseByCid(document.get("cid").toString());
                if(course==null||document==null)continue;
                String jsonStr="{\"avg_score\":"+document.get("avg_score")+",\"cname\":\""+course.getName()+"\",\"count_student\":\""+document.get("count_student")+"\"}";
                System.out.println(jsonStr);
                JSONObject obj= JSON.parseObject(jsonStr);
                jsonObjects.add(obj);
            }
            return jsonObjects;
        }catch (Exception e){
            System.out.println(e.toString());
            return null;
        }
    }
}
