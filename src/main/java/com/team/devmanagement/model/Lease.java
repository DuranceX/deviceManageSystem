package com.team.devmanagement.model;

public class Lease {
    Integer lid;
    Integer did;
    Integer uid;
    String dname;
    String duid;
    String user;
    String leaseDate;
    String backDate;

    public Lease() {
    }

    public Lease(Integer lid, Integer did, Integer uid, String dname, String duid, String user, String leaseDate, String backDate) {
        this.lid = lid;
        this.did = did;
        this.uid = uid;
        this.dname = dname;
        this.duid = duid;
        this.user = user;
        this.leaseDate = leaseDate;
        this.backDate = backDate;
    }

    public Integer getLid() {
        return lid;
    }

    public void setLid(Integer lid) {
        this.lid = lid;
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getDuid() {
        return duid;
    }

    public void setDuid(String duid) {
        this.duid = duid;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getLeaseDate() {
        return leaseDate;
    }

    public void setLeaseDate(String leaseDate) {
        this.leaseDate = leaseDate;
    }

    public String getBackDate() {
        return backDate;
    }

    public void setBackDate(String backDate) {
        this.backDate = backDate;
    }
}
