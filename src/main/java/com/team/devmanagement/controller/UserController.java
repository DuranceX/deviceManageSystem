package com.team.devmanagement.controller;

import com.team.devmanagement.model.Msg;
import com.team.devmanagement.model.User;
import com.team.devmanagement.service.UserService;
import com.team.devmanagement.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping ("/login")
    public Msg login(@RequestParam(value = "username") String username,@RequestParam(value="password") String password,
                     HttpSession session){
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

    @GetMapping("/update")
    public Msg update(@RequestBody User user,HttpSession session){
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
            msg.setMsg("更改用户密码失败");
        }else{
            msg.setStatus(200);
            msg.setMsg("更改用户密码成功");
        }
        return msg;
    }

    @GetMapping("/delete")
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

    @GetMapping("/logout")
    public Msg logout(HttpSession session){
        Msg msg = new Msg();
        session.removeAttribute("userid");
        msg.setStatus(200);
        msg.setMsg("注销成功");
        return msg;
    }
    @GetMapping("/test")
    public List<User> test(){
        return userService.getAllUsers();
    }

}
