package com.team.devmanagement.mapper;

import com.team.devmanagement.model.Lease;
import com.team.devmanagement.model.Maintain;
import jdk.jfr.internal.tool.Main;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MaintainMapper {
    int addItem(Maintain maintain);
    int deleteItemById(Integer mid);
    int updateItem(Maintain maintain);
    Maintain getItemById(Integer mid);
    List<Maintain> getItemsByUid(Integer uid);
    List<Maintain> getAllItems();
}
