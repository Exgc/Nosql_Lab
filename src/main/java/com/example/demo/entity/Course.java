package com.example.demo.entity;

public class Course {
    private String cid;
    private String fcid;
    private String name;
    private Integer credit;

    public Course(String cid, String fcid, String name, Integer credit) {
        this.cid = cid;
        this.fcid = fcid;
        this.name = name;
        this.credit = credit;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getFcid() {
        return fcid;
    }

    public void setFcid(String fcid) {
        this.fcid = fcid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }
}
