package com.example.demo.entity;

public class Student {
    private String sid;
    private String name;
    private String sex;
    private Integer age;
    private String birthday;
    private String dname;
    private String grade;

    public Student(String sid, String name, String sex, Integer age, String birthday, String dname, String grade) {
        this.sid = sid;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.birthday = birthday;
        this.dname = dname;
        this.grade = grade;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
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

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
