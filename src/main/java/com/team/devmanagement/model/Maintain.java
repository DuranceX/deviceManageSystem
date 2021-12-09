package com.team.devmanagement.model;

public class Maintain {
    Integer mid;
    Integer uid;
    Integer did;
    String duid;
    String dname;
    String mDate;
    String user;
    Float price;

    public Maintain() {
    }

    public Maintain(Integer mid, Integer uid, Integer did, String duid, String dname, String mDate, String user, Float price) {
        this.mid = mid;
        this.uid = uid;
        this.did = did;
        this.duid = duid;
        this.dname = dname;
        this.mDate = mDate;
        this.user = user;
        this.price = price;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
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

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}