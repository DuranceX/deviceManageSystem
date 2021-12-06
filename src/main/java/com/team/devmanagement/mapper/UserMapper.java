package com.team.devmanagement.mapper;

import com.team.devmanagement.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    int addUser(User user);
    int deleteUserById(Integer id);
    int updateUser(User user);
    User getUserById(Integer id);
    User query(String username,String password);
    List<User> getAllUsers();
}
