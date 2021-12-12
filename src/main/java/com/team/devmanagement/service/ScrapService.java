package com.team.devmanagement.service;

import com.team.devmanagement.mapper.ScrapMapper;
import com.team.devmanagement.model.Scrap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScrapService {
    @Autowired
    ScrapMapper scrapMapper;
    public int addItem(Scrap item){
        return scrapMapper.addItem(item);
    }
    public int updateItem(Scrap item){
        return scrapMapper.updateItem(item);
    }
    public Scrap getItemById(Integer sid){
        return scrapMapper.getItemById(sid);
    }
    public List<Scrap> getItemsByUid(Integer uid){
        return scrapMapper.getItemsByUid(uid);
    }
    public List<Scrap> getAllItems(){
        return scrapMapper.getAllItems();
    }
    public int deleteItemById(Integer sid){
        return scrapMapper.deleteItemById(sid);
    }
//    public User query(String username,String password){
//        return userMapper.query(username,password);
//    }
}
