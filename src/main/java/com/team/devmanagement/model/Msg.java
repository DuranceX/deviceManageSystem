package com.team.devmanagement.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "返回响应数据")
public class Msg {
    @ApiModelProperty(value="返回状态码")
    private int status=500;
    @ApiModelProperty(value="返回信息")
    private String msg="failed";
    @ApiModelProperty(value="返回对象")
    private Object obj=null;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
