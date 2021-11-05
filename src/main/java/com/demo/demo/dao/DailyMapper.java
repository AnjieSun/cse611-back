package com.demo.demo.dao;

import com.demo.demo.pojo.Daily;

public interface DailyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Daily record);

    int insertSelective(Daily record);

    Daily selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Daily record);

    int updateByPrimaryKey(Daily record);
}