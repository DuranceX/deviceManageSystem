package com.team.devmanagement.model;

public class DevicesInfo {
    String  Dname;
    Integer runningNum;
    Integer leasingNum;
    Integer maintainingNum;
    Integer scrapNum;

    public DevicesInfo(String dname, Integer runningNum, Integer leasingNum, Integer maintainingNum, Integer scrapNum) {
        Dname = dname;
        this.runningNum = runningNum;
        this.leasingNum = leasingNum;
        this.maintainingNum = maintainingNum;
        scrapNum = scrapNum;
    }

    public DevicesInfo() {
    }

    public String getDname() {
        return Dname;
    }

    public void setDname(String dname) {
        Dname = dname;
    }

    public Integer getRunningNum() {
        return runningNum;
    }

    public void setRunningNum(Integer runningNum) {
        this.runningNum = runningNum;
    }

    public Integer getLeasingNum() {
        return leasingNum;
    }

    public void setLeasingNum(Integer leasingNum) {
        this.leasingNum = leasingNum;
    }

    public Integer getMaintainingNum() {
        return maintainingNum;
    }

    public void setMaintainingNum(Integer maintainingNum) {
        this.maintainingNum = maintainingNum;
    }

    public Integer getScrapNum() {
        return scrapNum;
    }

    public void setScrapNum(Integer scrapNum) {
        scrapNum = scrapNum;
    }
}
