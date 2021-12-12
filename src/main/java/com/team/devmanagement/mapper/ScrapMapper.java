package com.team.devmanagement.mapper;

import com.team.devmanagement.model.Scrap;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ScrapMapper {
    int addItem(Scrap scarp);
    int deleteItemById(Integer sid);
    int updateItem(Scrap scarp);
    Scrap getItemById(Integer sid);
    List<Scrap> getItemsByUid(Integer uid);
    List<Scrap> getAllItems();
}
