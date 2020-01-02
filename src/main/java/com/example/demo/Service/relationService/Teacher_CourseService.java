package com.example.demo.Service.relationService;

import com.example.demo.dao.relationDao.Teacher_CourseDao;
import com.example.demo.entity.relation.Teacher_Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Max;
import java.util.List;

@Service
public class Teacher_CourseService {

    @Autowired
    private Teacher_CourseDao teacher_courseDao;

    public List<Teacher_Course> getInfoByCid(String cid){
        return teacher_courseDao.getInfoByCid(cid);
    }
}
