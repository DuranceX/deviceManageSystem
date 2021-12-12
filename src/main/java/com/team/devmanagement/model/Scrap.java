package com.team.devmanagement.model;

public class Scrap {
    Integer sid;
    Integer uid;
    Integer did;
    String duid;
    String dname;
    String sDate;
    String user;

    public Scrap() {
    }

    public Scrap(Integer sid, Integer uid, Integer did, String duid, String dname, String sDate, String user) {
        this.sid = sid;
        this.uid = uid;
        this.did = did;
        this.duid = duid;
        this.dname = dname;
        this.sDate = sDate;
        this.user = user;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public String getDuid() {
        return duid;
    }

    public void setDuid(String duid) {
        this.duid = duid;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getsDate() {
        return sDate;
    }

    public void setsDate(String sDate) {
        this.sDate = sDate;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}