package com.example.demo.dao.relationDao;

import com.alibaba.fastjson.JSON;
import com.example.demo.entity.Course;
import com.example.demo.entity.Student;
import com.example.demo.entity.relation.Student_Course;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class Student_CourseDao {

    @Autowired
    MongoTemplate mongoTemplate;

    public void insertInfo(Student_Course student_course) {
        mongoTemplate.save(student_course,"student_course");
    }

    public void updateInfo(Student_Course student_course){
        Query query=new Query(Criteria.where("sid").is(student_course.getSid()).and("cid").is(student_course.getCid()).and("tid").is(student_course.getTid()));
        Update update=new Update();
        update.set("score",student_course.getScore());
        mongoTemplate.updateFirst(query,update,"student_course");
    }

    public List<Student_Course> getCoursesBySid(String sid) {
        Query query=new Query(Criteria.where("sid").is(sid));
        List<Student_Course> student_courses=mongoTemplate.find(query,Student_Course.class,"student_course");
        return student_courses;
    }

    public List<Student_Course> getInfoBySid(String sid) {
        Query query=new Query(Criteria.where("sid").is(sid));
        return mongoTemplate.find(query,Student_Course.class,"student_course");
    }

    public void deleteInfoBySid(String sid) {
        Query query=new Query(Criteria.where("sid").is(sid));
        mongoTemplate.remove(query,Student_Course.class,"student_course");
    }

    public void deleteInfo(String sid, String cid, String tid) {
        Query query=new Query(Criteria.where("sid").is(sid).and("cid").is(cid).and("tid").is(tid));
        mongoTemplate.remove(query,Student_Course.class,"student_course");
    }

    public List<String> getDistinctCourse() {
        List<String> CidList=new ArrayList<>();
        Query query=new Query();
        CidList=mongoTemplate.findDistinct(query,"cid", "student_course",Student_Course.class,String.class);
        return CidList;
    }

    public List<Document> getAvgScore(){
        Aggregation aggregation=Aggregation.newAggregation(
                Aggregation.project("sid","score","cid","tid"),
                Aggregation.group("sid").avg("score").as("avg_score").first("sid").as("sid"),
                Aggregation.sort(new Sort(new Sort.Order(Sort.Direction.DESC,"avg_score"))),
                Aggregation.limit(10)
        );
        AggregationResults<Document> List=mongoTemplate.aggregate(aggregation,"student_course", Document.class);
        return List.getMappedResults();
    }

    public List<Document> getFirst10Course() {
        Aggregation aggregation=Aggregation.newAggregation(
                Aggregation.project("sid","score","cid","tid"),
                Aggregation.group("cid").count().as("count_student").first("cid").as("cid"),
                Aggregation.sort(new Sort(new Sort.Order(Sort.Direction.DESC,"count_student"))),
                Aggregation.limit(10)
        );
        AggregationResults<Document> List=mongoTemplate.aggregate(aggregation,"student_course", Document.class);
        return List.getMappedResults();
    }

    public List<Document> getFirst10Student() {
        Aggregation aggregation=Aggregation.newAggregation(
                Aggregation.project("sid","score","cid","tid"),
                Aggregation.group("sid").count().as("count_course").first("sid").as("sid"),
                Aggregation.sort(new Sort(new Sort.Order(Sort.Direction.DESC,"count_course"))),
                Aggregation.limit(10)
        );
        AggregationResults<Document> List=mongoTemplate.aggregate(aggregation,"student_course", Document.class);
        return List.getMappedResults();
    }

    public List<Document> getBestClassForStudent(){
        Aggregation aggregation=Aggregation.newAggregation(
                Aggregation.project("sid","score","cid","tid"),
                Aggregation.group("sid").max("score").as("max_score").first("sid").as("sid").first("cid").as("cid")
        );
        AggregationResults<Document> List=mongoTemplate.aggregate(aggregation,"student_course",Document.class);
        return List.getMappedResults();
    }

    public List<Document> getBestStudentForCourse(){
        Aggregation aggregation=Aggregation.newAggregation(
                Aggregation.project("sid","score","cid","tid"),
                Aggregation.group("cid").max("score").as("max_score").first("sid").as("sid").first("cid").as("cid")
        );
        AggregationResults<Document> List=mongoTemplate.aggregate(aggregation,"student_course",Document.class);
        return List.getMappedResults();
    }

    public List<Document> getInfoForCourse(){
        Aggregation aggregation=Aggregation.newAggregation(
                Aggregation.project("sid","score","cid","tid"),
                Aggregation.group("cid").avg("score").as("avg_score").count().as("count_student").first("sid").as("sid").first("cid").as("cid")
        );
        AggregationResults<Document> List=mongoTemplate.aggregate(aggregation,"student_course",Document.class);
        return List.getMappedResults();
    }

    public List<Document> getCourseAvgScore() {
        Aggregation aggregation=Aggregation.newAggregation(
                Aggregation.project("sid","score","cid","tid"),
                Aggregation.group("cid").avg("score").as("avg_score").first("cid").as("cid"),
                Aggregation.sort(new Sort(new Sort.Order(Sort.Direction.DESC,"avg_score"))),
                Aggregation.limit(10)
        );
        AggregationResults<Document> List=mongoTemplate.aggregate(aggregation,"student_course", Document.class);
        return List.getMappedResults();
    }

}
