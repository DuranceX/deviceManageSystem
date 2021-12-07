package com.team.devmanagement.service;

import com.team.devmanagement.mapper.DeviceMapper;
import com.team.devmanagement.mapper.PurchaseMapper;
import com.team.devmanagement.model.Device;
import com.team.devmanagement.model.Purchase;
import com.team.devmanagement.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseService {
    @Autowired
    PurchaseMapper purchaseMapper;
    public int addItem(Purchase item){
        return purchaseMapper.addItem(item);
    }
    public int updateItem(Purchase item){
        return purchaseMapper.updateItem(item);
    }
    public Purchase getItemById(Integer pid){
        return purchaseMapper.getItemById(pid);
    }
    public List<Purchase> getItemsByUid(Integer uid){
        return purchaseMapper.getItemsByUid(uid);
    }
    public List<Purchase> getAllItems(){
        return purchaseMapper.getAllItems();
    }
    public int deletePurchaseById(Integer pid){
        return purchaseMapper.deleteItemById(pid);
    }
//    public User query(String username,String password){
//        return userMapper.query(username,password);
//    }
}
