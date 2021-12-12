package com.team.devmanagement.service;

import com.team.devmanagement.mapper.UserMapper;
import com.team.devmanagement.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;
    public int addUser(User user){
        return userMapper.addUser(user);
    }
    public int updateUser(User user){
        return userMapper.updateUser(user);
    }
    public User getUserById(Integer uid){
        return userMapper.getUserById(uid);
    }
    public List<User> getAllUsers(){
        return userMapper.getAllUsers();
    }
    public int updateUserPwd(String password,String username){
        return userMapper.updateUserPwd(password,username);
    }
    public int deleteUserById(Integer uid){
        return userMapper.deleteUserById(uid);
    }
    public User query(String username,String password){
        return userMapper.query(username,password);
    }
}
