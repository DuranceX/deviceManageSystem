package com.team.devmanagement.service;

import com.team.devmanagement.mapper.LeaseMapper;
import com.team.devmanagement.mapper.PurchaseMapper;
import com.team.devmanagement.model.Lease;
import com.team.devmanagement.model.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaseService {
    @Autowired
    LeaseMapper leaseMapper;
    public int addItem(Lease item){
        return leaseMapper.addItem(item);
    }
    public int updateItem(Lease item){
        return leaseMapper.updateItem(item);
    }
    public Lease getItemById(Integer pid){
        return leaseMapper.getItemById(pid);
    }
    public List<Lease> getItemsByUid(Integer uid){
        return leaseMapper.getItemsByUid(uid);
    }
    public List<Lease> getAllItems(){
        return leaseMapper.getAllItems();
    }
    public int deleteItemById(Integer pid){
        return leaseMapper.deleteItemById(pid);
    }
//    public User query(String username,String password){
//        return userMapper.query(username,password);
//    }
}
