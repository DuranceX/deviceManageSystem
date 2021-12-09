package com.team.devmanagement.controller;

import com.team.devmanagement.model.Lease;
import com.team.devmanagement.model.Msg;
import com.team.devmanagement.model.Purchase;
import com.team.devmanagement.model.User;
import com.team.devmanagement.service.LeaseService;
import com.team.devmanagement.service.PurchaseService;
import com.team.devmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
public class LeaseController {
    @Autowired
    LeaseService leaseService;
    @Autowired
    UserService userService;

    @PostMapping("/lease/add")
    public Msg addItem(@RequestBody Lease item, HttpSession session){
        Msg msg = new Msg();
        Object uid = session.getAttribute("userid");
        if(uid==null) {
            msg.setMsg("请先登陆");
            return msg;
        }
        Integer curUid = Integer.parseInt(uid.toString());
        User curUser = userService.getUserById(curUid);
        if(!item.getUid().equals(curUid)){
            msg.setMsg("不可添加其他用户的购买记录！");
            return msg;
        }
        int result = leaseService.addItem(item);
        //如果devices表里面没有该设备，则新增

        if(result>0){
            msg.setStatus(200);
            msg.setMsg("添加成功");
        }else{
            msg.setMsg("添加失败，未知错误");
        }
        return msg;
    }
    @PostMapping("/lease/delete")
    public Msg deleteItem(@RequestParam Integer id, HttpSession session){
        Msg msg = new Msg();
        Object uid = session.getAttribute("userid");
        if(uid==null) {
            msg.setMsg("请先登陆");
            return msg;
        }
        Integer curUid = Integer.parseInt(uid.toString());
        Lease item = leaseService.getItemById(id);
        if(!item.getUid().equals(curUid)){
            msg.setMsg("不可删除其他用户的购买记录！");
            return msg;
        }
        int result = leaseService.deleteItemById(id);
        if(result>0){
            msg.setStatus(200);
            msg.setMsg("删除成功");
        }else{
            msg.setMsg("删除失败，未知错误");
        }
        return msg;

    }

    @PostMapping("/lease/update")
    public Msg update(@RequestBody Lease item, HttpSession session){
        Msg msg = new Msg();
        Object uid = session.getAttribute("userid");
        if(uid==null) {
            msg.setMsg("请先登陆");
            return msg;
        }
        Integer curUid = Integer.parseInt(uid.toString());
        if(!item.getUid().equals(curUid)){
            msg.setMsg("不可更改其他用户的购买记录！");
            return msg;
        }
        int result = leaseService.updateItem(item);
        if(result>0){
            msg.setStatus(200);
            msg.setMsg("删除成功");
        }else{
            msg.setMsg("删除失败，未知错误");
        }
        return msg;

    }

    @PostMapping("/lease/getItems")
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
            msg.setObj(leaseService.getItemsByUid(curUid));
            msg.setMsg("返回数据成功");
            msg.setStatus(200);
            return msg;
        }
        msg.setObj(leaseService.getAllItems());
        msg.setMsg("返回数据成功");
        msg.setStatus(200);
        return msg;
    }
}
