package com.team.devmanagement.mapper;

import com.team.devmanagement.model.Device;
import com.team.devmanagement.model.Purchase;
import com.team.devmanagement.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PurchaseMapper {
    int addItem(Purchase purchase);
    int deleteItemById(Integer pid);
    int updateItem(Purchase purchase);
    Purchase getItemById(Integer pid);
    List<Purchase> getItemsByUid(Integer uid);
    List<Purchase> getAllItems();
}
