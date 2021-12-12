package com.team.devmanagement.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.nio.channels.FileLock;
import java.util.Date;

@ApiModel(description = "购买Bean")
public class Purchase {
    @ApiModelProperty(value="购买项id")
    Integer pid;
    @ApiModelProperty(value="用户id")
    Integer uid;
    @ApiModelProperty(value="设备名称")
    String dname;
    @ApiModelProperty(value="购买设备数量")
    Integer amount;
    @ApiModelProperty(value="经手人")
    String buyer;
    @ApiModelProperty(value="设备号")
    String duid;
    @ApiModelProperty(value="购买日期")
    String pdate;
    @ApiModelProperty(value="购买经费")
    Float price;

    public Purchase() {
    }

    public Purchase(Integer pid, Integer uid, String dname, Integer amount, String buyer, String duid, String pdate, Float price) {
        this.pid = pid;
        this.uid = uid;
        this.dname = dname;
        this.amount = amount;
        this.buyer = buyer;
        this.duid = duid;
        this.pdate = pdate;
        this.price = price;
    }

    public String getDuid() {
        return duid;
    }

    public void setDuid(String duid) {
        this.duid = duid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }


    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getPdate() {
        return pdate;
    }

    public void setPdate(String pdate) {
        this.pdate = pdate;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
