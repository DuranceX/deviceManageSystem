package com.team.devmanagement.model;


public class Device {
    Integer did;
    String dname;
    String dstatus;
    String duid;

    public Device(Integer did, String dname, String dstatus, String duid) {
        this.did = did;
        this.dname = dname;
        this.dstatus = dstatus;
        this.duid = duid;
    }

    public Device(String dname, String dstatus, String duid) {
        this.dname = dname;
        this.dstatus = dstatus;
        this.duid = duid;
    }

    public Device() {
    }

    public String getDstatus() {
        return dstatus;
    }

    public void setDstatus(String dstatus) {
        this.dstatus = dstatus;
    }

    public String getDuid() {
        return duid;
    }

    public void setDuid(String duid) {
        this.duid = duid;
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }
}
