package com.example.demo.Service.relationService;


import com.example.demo.dao.relationDao.Student_CourseDao;
import com.example.demo.entity.Course;
import com.example.demo.entity.relation.Student_Course;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Student_CourseService {

    @Autowired
    private Student_CourseDao student_courseDao;


    public void insertInfo(Student_Course student_course) {
        student_courseDao.insertInfo(student_course);
    }

    public void updateInfo(Student_Course student_course){
         student_courseDao.updateInfo(student_course);
    }

    public List<Student_Course> getCoursesBySid(String sid) {
        return student_courseDao.getCoursesBySid(sid);
    }

    public List<Student_Course> getInfoBySid(String sid) {
        return student_courseDao.getInfoBySid(sid);
    }

    public void deleteInfoBySid(String sid) {
        student_courseDao.deleteInfoBySid(sid);
    }

    public void deleteInfo(String sid, String cid, String tid) {
        student_courseDao.deleteInfo(sid,cid,tid);
    }

    public List<String> getDistinctCourse() {
        return student_courseDao.getDistinctCourse();
    }

    public List<Document> getAvgScore(){
        return student_courseDao.getAvgScore();
    }

    public List<Document> getFirst10Course() {
        return student_courseDao.getFirst10Course();
    }

    public List<Document> getFirst10Student() {
        return student_courseDao.getFirst10Student();
    }

    public List<Document> getBestClassForStdent() {
        return student_courseDao.getBestClassForStudent();
    }

    public List<Document> getBestStudentForCourse() {
        return student_courseDao.getBestStudentForCourse();
    }

    public List<Document> getCourseAvgScore() {
        return student_courseDao.getCourseAvgScore();
    }

    public List<Document> getInfoForCourse() {
        return student_courseDao.getInfoForCourse();
    }
}
