package com.team.devmanagement.controller;

import com.team.devmanagement.model.Msg;
import com.team.devmanagement.model.User;
import com.team.devmanagement.service.UserService;
import com.team.devmanagement.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping ("/login")
    public Msg login(@RequestParam(value = "username") String username,@RequestParam(value="password") String password){
        Msg msg = new Msg();
        password = Utils.pwdEncrypt(username,password);
        User user = userService.query(username,password);
        msg.setObj(user);
        msg.setMsg("传入的值："+username+" "+password);
        if(user != null) {
            msg.setStatus(200);
            msg.setMsg("登陆成功");
            msg.setObj(user);
        }
        return msg;
    }

    @PostMapping("/updateUser")
    public Msg update(@RequestBody User user){
        Msg msg = new Msg();
        int result = userService.updateUser(user);
        if(result==0){
            msg.setMsg("更改用户信息失败");
        }else{
            msg.setStatus(200);
            msg.setMsg("更改用户信息成功");
        }
        return msg;
    }

    @PostMapping("/deleteUserById")
    public Msg deleteUser(@RequestParam Integer id){
        Msg msg = new Msg();
        int result = userService.deleteUserById(id);
        if(result==0){
            msg.setMsg("删除用户信息失败");
        }else{
            msg.setStatus(200);
            msg.setMsg("删除用户信息成功");
        }
        return msg;
    }


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
    @GetMapping("/test")
    public List<User> test(){
        return userService.getAllUsers();
    }

}
