package com.team.devmanagement.controller;

import com.team.devmanagement.model.Msg;
import com.team.devmanagement.model.User;
import com.team.devmanagement.service.UserService;
import com.team.devmanagement.utils.Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@Api(tags = "用户接口")
public class UserController {
    @Autowired
    UserService userService;

    @ApiOperation(value="登陆",notes="用户输入账号和密码登陆系统")
    @ApiImplicitParams({
            @ApiImplicitParam(name="username",value="用户名",required = true),
            @ApiImplicitParam(name="password",value="密码",required = true),
    })
    @PostMapping ("/login")
    public Msg login(@RequestParam(value = "username") String username,@RequestParam(value="password") String password,
                     @ApiIgnore HttpSession session){
        Msg msg = new Msg();
        password = Utils.pwdEncrypt(username,password);
        User user = userService.query(username,password);
        msg.setObj(user);
//      错误时会输出这个信息
        msg.setMsg("传入的值："+username+" "+password);
        if(user != null) {
            msg.setStatus(200);
            msg.setMsg("登陆成功");
            msg.setObj(user);
            session.setAttribute("userid",user.getUid());
        }

        return msg;
    }
    @ApiOperation(value="更新用户信息",notes = "更新用户权限信息")
    @PostMapping("/update")
    public Msg update(@RequestBody User user,@ApiIgnore HttpSession session){
        Msg msg = new Msg();
        Object uid = session.getAttribute("userid");
        if(uid==null) {
            msg.setMsg("请先登陆");
            return msg;
        }
        User curUser = userService.getUserById(Integer.parseInt(uid.toString()));
        if(!curUser.getAdmin()){
            msg.setMsg("没有权限");
            return msg;
        }

        int result = userService.updateUser(user);
        if(result==0){
            msg.setMsg("更改用户权限失败");
        }else{
            msg.setStatus(200);
            msg.setMsg("更改用户权限成功");
        }
        return msg;
    }

    @ApiOperation(value="更改用户密码",notes = "更新用户密码信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="username",value="用户名",required = true),
            @ApiImplicitParam(name="password",value="密码",required = true),
    })
    @PostMapping("/updateUserPwd")
    public Msg updateUserPwd(@RequestParam String password,@RequestParam String username,@ApiIgnore HttpSession session){
        Msg msg = new Msg();
        Object uid = session.getAttribute("userid");
        if(uid==null) {
            msg.setMsg("请先登陆");
            return msg;
        }
        User curUser = userService.getUserById(Integer.parseInt(uid.toString()));
        if(!curUser.getAdmin()){
            msg.setMsg("没有权限");
            return msg;
        }
        password = Utils.pwdEncrypt(username,password);
        int result = userService.updateUserPwd(password,username);
        if(result==0){
            msg.setMsg("更改用户密码失败");
        }else{
            msg.setStatus(200);
            msg.setMsg("更改用户密码成功");
        }
        return msg;
    }
    @ApiOperation(value="删除用户信息",notes = "删除用户信息")
    @ApiImplicitParam(name="id",value="用户id",required = true)
    @PostMapping("/delete")
    public Msg deleteUser(@RequestParam Integer id,HttpSession session){
        Msg msg = new Msg();
        Object uid = session.getAttribute("userid");
        if(uid==null) {
            msg.setMsg("请先登陆");
            return msg;
        }
        User curUser = userService.getUserById(Integer.parseInt(uid.toString()));
        if(!curUser.getAdmin()){
            msg.setMsg("没有权限");
            return msg;
        }
        int result = userService.deleteUserById(id);
        if(result==0){
            msg.setMsg("删除用户信息失败");
        }else{
            msg.setStatus(200);
            msg.setMsg("删除用户信息成功");
        }
        return msg;
    }

    @ApiOperation(value="注册",notes = "用户注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name="username",value="用户名",required = true),
            @ApiImplicitParam(name="password",value="密码",required = true),
    })
    @PostMapping("/reg")
    public Msg register(@RequestParam(value = "username") String username,@RequestParam(value="password") String password){
        Msg msg = new Msg();
        List<User> userList = userService.getAllUsers();
        //1.检查是否重复
        for(User user:userList){
            if( user.getUsername().equals(username)){
                msg.setMsg("用户名重复！");
                return msg;
            }
        }
        //2.插入数据库
        User user = new User();
        user.setUsername(username);
        user.setPassword(Utils.pwdEncrypt(username,password));
        int result = userService.addUser(user);
        if(result==0){
            msg.setMsg("未知错误");

        }else{
            msg.setMsg("注册成功");
            msg.setStatus(200);
        }
        return msg;


    }

    @ApiOperation(value="注销",notes = "用户注销")
    @PostMapping("/logout")
    public Msg logout(@ApiIgnore HttpSession session){
        Msg msg = new Msg();
        session.removeAttribute("userid");
        msg.setStatus(200);
        msg.setMsg("注销成功");
        return msg;
    }
    @ApiOperation(value="获取所有用户",notes = "展示所有用户信息")
    @PostMapping("/getUsers")
    public List<User> getUsers(){
        return userService.getAllUsers();
    }

}
