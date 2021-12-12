package com.team.devmanagement.controller;

import com.team.devmanagement.model.Device;
import com.team.devmanagement.model.Msg;
import com.team.devmanagement.model.User;
import com.team.devmanagement.service.DeviceService;
import com.team.devmanagement.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;


@RestController
public class DeviceController {
    @Autowired
    DeviceService deviceService;
    @Autowired
    UserService userService;

    @PostMapping("/device/add")
    public Msg addItem(@RequestBody Device item, HttpSession session){
        Msg msg = new Msg();
        Object uid = session.getAttribute("userid");
        if(uid==null) {
            msg.setMsg("请先登陆");
            return msg;
        }
        Integer curUid = Integer.parseInt(uid.toString());
        User curUser = userService.getUserById(curUid);
        if(!curUser.getAdmin()){
            msg.setMsg("普通用户不可直接增加库存记录！请联系管理员！");
            return msg;
        }
        int result = deviceService.addDevice(item);
        //如果devices表里面没有该设备，则新增

        if(result>0){
            msg.setStatus(200);
            msg.setMsg("添加成功");
        }else{
            msg.setMsg("添加失败，未知错误");
        }
        return msg;
    }
    @PostMapping("/device/delete")
    public Msg deleteItem(@RequestParam Integer id, HttpSession session){
        Msg msg = new Msg();
        Object uid = session.getAttribute("userid");
        if(uid==null) {
            msg.setMsg("请先登陆");
            return msg;
        }
        Integer curUid = Integer.parseInt(uid.toString());
        User curUser = userService.getUserById(curUid);
        if(!curUser.getAdmin()){
            msg.setMsg("普通用户不可删除库存记录！请联系管理员！");
            return msg;
        }
        int result = deviceService.deleteDeviceById(id);
        if(result>0){
            msg.setStatus(200);
            msg.setMsg("删除成功");
        }else{
            msg.setMsg("删除失败，未知错误");
        }
        return msg;

    }

    @PostMapping("/device/update")
    public Msg update(@RequestBody Device item, HttpSession session){
        Msg msg = new Msg();
        Object uid = session.getAttribute("userid");
        if(uid==null) {
            msg.setMsg("请先登陆");
            return msg;
        }
        Integer curUid = Integer.parseInt(uid.toString());
        User curUser = userService.getUserById(curUid);
        if(!curUser.getAdmin()){
            msg.setMsg("普通用户不可更改库存记录！请联系管理员");
            return msg;
        }
        int result = deviceService.updateDevice(item);
        if(result>0){
            msg.setStatus(200);
            msg.setMsg("更新成功");
        }else{
            msg.setMsg("更新失败，未知错误");
        }
        return msg;

    }

    @PostMapping("/device/getItems")
    public Msg getItems(HttpSession session){
        Msg msg = new Msg();
        Object uid = session.getAttribute("userid");
        if(uid==null) {
            msg.setMsg("请先登陆");
            return msg;
        }
        msg.setObj(deviceService.getAllDevices());
        msg.setMsg("返回数据成功");
        msg.setStatus(200);
        return msg;
    }

    @PostMapping("/device/group")
    @ApiOperation(value = "暂时不可用")
    public Msg group(@ApiIgnore HttpSession session){
        Msg msg = new Msg();
        Object uid = session.getAttribute("userid");
        if(uid==null) {
            msg.setMsg("请先登陆");
            return msg;
        }
        msg.setObj(deviceService.getDevicesGroupByDnameAndDstatus());
        msg.setMsg("返回数据成功");
        msg.setStatus(200);
        return msg;
    }

}
