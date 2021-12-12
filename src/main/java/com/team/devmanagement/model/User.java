package com.team.devmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "用户Bean")
public class User {
    @ApiModelProperty(value="用户id")
    private Integer uid;
    @ApiModelProperty(value="用户名")
    private String username;
    @ApiModelProperty(value="是否为管理员")
    private Boolean admin;
    @ApiModelProperty(value="密码")
    @JsonIgnore
    private String password;


    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User() {
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }
}
