package com.team.devmanagement.controller;

import com.team.devmanagement.model.Device;
import com.team.devmanagement.model.Msg;
import com.team.devmanagement.model.Purchase;
import com.team.devmanagement.model.User;
import com.team.devmanagement.service.DeviceService;
import com.team.devmanagement.service.PurchaseService;
import com.team.devmanagement.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;

@Api(tags="购买接口")
@RestController
public class PurchaseController {
    @Autowired
    PurchaseService purchaseService;
    @Autowired
    DeviceService deviceService;
    @Autowired
    UserService userService;

    @ApiOperation(value="新增购买信息",notes = "新增购买设备记录，新增项目的时候不要传pid过来，系统会自动生成")
    @PostMapping("/purchase/add")
    public Msg addItem(@RequestBody Purchase item,@ApiIgnore HttpSession session){
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
        int result = purchaseService.addItem(item);
        //如果devices表里面没有该设备，则新增

        if(result>0){
            //添加设备表数据
            String[] duids = item.getDuid().split(",");
            for(String duid:duids){
                deviceService.addDevice(new Device(item.getDname(),"运行中",duid));
            }
            msg.setStatus(200);
            msg.setMsg("添加成功");
        }else{
            msg.setMsg("添加失败，未知错误");
        }
        return msg;
    }
    @ApiOperation(value="删除购买信息",notes="删除购买信息")
    @PostMapping("/purchase/delete")
    public Msg deleteItem(@RequestParam Integer id,@ApiIgnore HttpSession session){
        Msg msg = new Msg();
        Object uid = session.getAttribute("userid");
        if(uid==null) {
            msg.setMsg("请先登陆");
            return msg;
        }
        Integer curUid = Integer.parseInt(uid.toString());
        Purchase item = purchaseService.getItemById(id);
        if(!item.getUid().equals(curUid)){
            msg.setMsg("不可删除其他用户的购买记录！");
            return msg;
        }
        int result = purchaseService.deleteItemById(id);
        if(result>0){
            //添加设备表数据
            String[] duids = item.getDuid().split(",");
            for(String duid:duids){
                deviceService.deleteDeviceById(deviceService.getDeviceByDuid(duid).getDid());
            }
            msg.setStatus(200);
            msg.setMsg("删除成功");
        }else{
            msg.setMsg("删除失败，未知错误");
        }
        return msg;

    }
    @ApiOperation(value="更新购买信息",notes = "更新购买信息")
    @PostMapping("/purchase/update")
    public Msg update(@RequestBody Purchase item,@ApiIgnore HttpSession session){
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
        int result = purchaseService.updateItem(item);
        if(result>0){
            String[] duids = item.getDuid().split(",");
            for(String duid:duids){
                deviceService.updateDevice(new Device(deviceService.getDeviceByDuid(duid).getDid(),item.getDname(),"运行中",duid));
            }
            msg.setStatus(200);
            msg.setMsg("更新成功");
        }else{
            msg.setMsg("更新失败，未知错误");
        }
        return msg;

    }
    @ApiOperation(value="获取所有购买信息",notes = "普通用户只可以看本用户的购买信息，管理员可以看所有购买信息")
    @PostMapping("/purchase/getItems")
    public Msg getItems(@ApiIgnore HttpSession session){
        Msg msg = new Msg();
        Object uid = session.getAttribute("userid");
        if(uid==null) {
            msg.setMsg("请先登陆");
            return msg;
        }
        Integer curUid = Integer.parseInt(uid.toString());
        User curUser = userService.getUserById(curUid);
        if(!curUser.getAdmin()){
            msg.setObj(purchaseService.getItemsByUid(curUid));
            msg.setMsg("返回数据成功");
            msg.setStatus(200);
            return msg;
        }
        msg.setObj(purchaseService.getAllItems());
        msg.setMsg("返回数据成功");
        msg.setStatus(200);
        return msg;
    }
}
