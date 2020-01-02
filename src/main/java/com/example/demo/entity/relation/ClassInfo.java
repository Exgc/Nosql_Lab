package com.example.demo.entity.relation;

public class ClassInfo {
    private String cid;
    private String tname;
    private String tid;
    private String fcid;
    private Integer credit;
    private String name;

    public ClassInfo(String cid, String tname,String tid, String fcid, Integer credit, String name) {
        this.cid = cid;
        this.tname = tname;
        this.fcid = fcid;
        this.credit = credit;
        this.name = name;
        this.tid=tid;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getFcid() {
        return fcid;
    }

    public void setFcid(String fcid) {
        this.fcid = fcid;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
