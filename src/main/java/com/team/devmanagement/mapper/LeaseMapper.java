package com.team.devmanagement.mapper;

import com.team.devmanagement.model.Lease;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LeaseMapper {
    int addItem(Lease lease);
    int deleteItemById(Integer lid);
    int updateItem(Lease lease);
    Lease getItemById(Integer lid);
    List<Lease> getItemsByUid(Integer uid);
    List<Lease> getAllItems();
}
