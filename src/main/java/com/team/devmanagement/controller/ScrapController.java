package com.team.devmanagement.controller;

import com.team.devmanagement.model.Device;
import com.team.devmanagement.model.Scrap;
import com.team.devmanagement.model.Msg;
import com.team.devmanagement.model.User;
import com.team.devmanagement.service.DeviceService;
import com.team.devmanagement.service.ScrapService;
import com.team.devmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class ScrapController {
    @Autowired
    ScrapService scrapService;
    @Autowired
    UserService userService;
    @Autowired
    DeviceService deviceService;

    @PostMapping("/scrap/add")
    public Msg addItem(@RequestBody Scrap item, HttpSession session){
        Msg msg = new Msg();
        Object uid = session.getAttribute("userid");
        if(uid==null) {
            msg.setMsg("请先登陆");
            return msg;
        }
        Integer curUid = Integer.parseInt(uid.toString());
        User curUser = userService.getUserById(curUid);
        if(!item.getUid().equals(curUid)){
            msg.setMsg("不可添加其他用户的报废记录！");
            return msg;
        }
        int result = scrapService.addItem(item);
        //如果devices表里面没有该设备，则新增

        if(result>0){
            //更改设备状态
            Device device = deviceService.getDeviceById(item.getDid());
            device.setDstatus("已报废");
            deviceService.updateDevice(device);
            msg.setStatus(200);
            msg.setMsg("添加成功");
        }else{
            msg.setMsg("添加失败，未知错误");
        }
        return msg;
    }
    @PostMapping("/scrap/delete")
    public Msg deleteItem(@RequestParam Integer id, HttpSession session){
        Msg msg = new Msg();
        Object uid = session.getAttribute("userid");
        if(uid==null) {
            msg.setMsg("请先登陆");
            return msg;
        }
        Integer curUid = Integer.parseInt(uid.toString());
        Scrap item = scrapService.getItemById(id);
        if(!item.getUid().equals(curUid)){
            msg.setMsg("不可删除其他用户的报废记录！");
            return msg;
        }
        int result = scrapService.deleteItemById(id);
        if(result>0){
            //更改设备状态
            Device device = deviceService.getDeviceById(item.getDid());
            device.setDstatus("运行中");
            deviceService.updateDevice(device);
            msg.setStatus(200);
            msg.setMsg("删除成功");
        }else{
            msg.setMsg("删除失败，未知错误");
        }
        return msg;

    }

    @PostMapping("/scrap/update")
    public Msg update(@RequestBody Scrap item, HttpSession session){
        Msg msg = new Msg();
        Object uid = session.getAttribute("userid");
        if(uid==null) {
            msg.setMsg("请先登陆");
            return msg;
        }
        Integer curUid = Integer.parseInt(uid.toString());
        if(!item.getUid().equals(curUid)){
            msg.setMsg("不可更改其他用户的报废记录！");
            return msg;
        }
        int result = scrapService.updateItem(item);
        if(result>0){
            msg.setStatus(200);
            msg.setMsg("更新成功");
        }else{
            msg.setMsg("更新失败，未知错误");
        }
        return msg;

    }

    @PostMapping("/scrap/getItems")
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
            msg.setObj(scrapService.getItemsByUid(curUid));
            msg.setMsg("返回数据成功");
            msg.setStatus(200);
            return msg;
        }
        msg.setObj(scrapService.getAllItems());
        msg.setMsg("返回数据成功");
        msg.setStatus(200);
        return msg;
    }
}
