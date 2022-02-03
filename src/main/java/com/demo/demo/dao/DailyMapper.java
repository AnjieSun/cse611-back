package com.demo.demo.dao;

import com.demo.demo.pojo.Daily;
import com.demo.demo.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DailyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Daily record);

    int insertSelective(Daily record);

    Daily selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Daily record);

    int updateByPrimaryKey(Daily record);

    Daily findByIdAndDate(@Param("id") int id, @Param("date") String date);

    List<Daily> findMonthdate(Integer id);

    List<Daily> find14days(Integer id);

    List<Daily> find7days(Integer id);

    List<Daily> find3days(Integer id);

}