package com.team.devmanagement.service;

import com.team.devmanagement.mapper.MaintainMapper;
import com.team.devmanagement.model.Maintain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaintainService {
    @Autowired
    MaintainMapper maintainMapper;
    public int addItem(Maintain item){
        return maintainMapper.addItem(item);
    }
    public int updateItem(Maintain item){
        return maintainMapper.updateItem(item);
    }
    public Maintain getItemById(Integer mid){
        return maintainMapper.getItemById(mid);
    }
    public List<Maintain> getItemsByUid(Integer uid){
        return maintainMapper.getItemsByUid(uid);
    }
    public List<Maintain> getAllItems(){
        return maintainMapper.getAllItems();
    }
    public int deleteItemById(Integer mid){
        return maintainMapper.deleteItemById(mid);
    }
//    public User query(String username,String password){
//        return userMapper.query(username,password);
//    }
}
