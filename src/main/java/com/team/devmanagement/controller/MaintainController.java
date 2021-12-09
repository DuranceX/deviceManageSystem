package com.team.devmanagement.controller;

import com.team.devmanagement.model.Maintain;
import com.team.devmanagement.model.Msg;
import com.team.devmanagement.model.User;
//import com.team.devmanagement.service.maintainService;
import com.team.devmanagement.service.MaintainService;
import com.team.devmanagement.service.UserService;
import jdk.jfr.internal.tool.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class MaintainController {
    @Autowired
    MaintainService maintainService;
    @Autowired
    UserService userService;

    @PostMapping("/maintain/add")
    public Msg addItem(@RequestBody Maintain item, HttpSession session){
        Msg msg = new Msg();
        Object uid = session.getAttribute("userid");
        if(uid==null) {
            msg.setMsg("请先登陆");
            return msg;
        }
        Integer curUid = Integer.parseInt(uid.toString());
        User curUser = userService.getUserById(curUid);
        if(!item.getUid().equals(curUid)){
            msg.setMsg("不可添加其他用户的维修记录！");
            return msg;
        }
        int result = maintainService.addItem(item);
        //如果devices表里面没有该设备，则新增

        if(result>0){
            msg.setStatus(200);
            msg.setMsg("添加成功");
        }else{
            msg.setMsg("添加失败，未知错误");
        }
        return msg;
    }
    @PostMapping("/maintain/delete")
    public Msg deleteItem(@RequestParam Integer id, HttpSession session){
        Msg msg = new Msg();
        Object uid = session.getAttribute("userid");
        if(uid==null) {
            msg.setMsg("请先登陆");
            return msg;
        }
        Integer curUid = Integer.parseInt(uid.toString());
        Maintain item = maintainService.getItemById(id);
        if(!item.getUid().equals(curUid)){
            msg.setMsg("不可删除其他用户的维修记录！");
            return msg;
        }
        int result = maintainService.deleteItemById(id);
        if(result>0){
            msg.setStatus(200);
            msg.setMsg("删除成功");
        }else{
            msg.setMsg("删除失败，未知错误");
        }
        return msg;

    }

    @PostMapping("/maintain/update")
    public Msg update(@RequestBody Maintain item, HttpSession session){
        Msg msg = new Msg();
        Object uid = session.getAttribute("userid");
        if(uid==null) {
            msg.setMsg("请先登陆");
            return msg;
        }
        Integer curUid = Integer.parseInt(uid.toString());
        if(!item.getUid().equals(curUid)){
            msg.setMsg("不可更改其他用户的维修记录！");
            return msg;
        }
        int result = maintainService.updateItem(item);
        if(result>0){
            msg.setStatus(200);
            msg.setMsg("更新成功");
        }else{
            msg.setMsg("更新失败，未知错误");
        }
        return msg;

    }

    @PostMapping("/maintain/getItems")
    public Msg getItems(HttpSession session){
        Msg msg = new Msg();
        Object uid = session.getAttribute("userid");
        if(uid==null) {
            msg.setMsg("请先登陆");
            return msg;
        }
        Integer curUid = Integer.parseInt(uid.toString());
        User curUser = userService.getUserById(curUid);
        if(!curUser.getAdmin()){
            msg.setObj(maintainService.getItemsByUid(curUid));
            msg.setMsg("返回数据成功");
            msg.setStatus(200);
            return msg;
        }
        msg.setObj(maintainService.getAllItems());
        msg.setMsg("返回数据成功");
        msg.setStatus(200);
        return msg;
    }
}
