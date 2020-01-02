package com.example.demo.entity.relation;

public class Student_Course {
    private String sid;
    private String cid;
    private String tid;
    private Integer score;

    public Student_Course(String sid, String cid, String tid, Integer score) {
        this.sid = sid;
        this.cid = cid;
        this.tid = tid;
        this.score = score;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
