package com.example.demo.entity;

public class Teacher {
    private String tid;
    private String name;
    private String sex;
    private String dname;
    private Integer age;

    public Teacher(String tid, String name, String sex, String dname, Integer age) {
        this.tid = tid;
        this.name = name;
        this.sex = sex;
        this.dname = dname;
        this.age = age;
    }

    public Teacher() {

    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
