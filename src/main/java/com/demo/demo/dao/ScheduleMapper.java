package com.demo.demo.dao;

import com.demo.demo.pojo.Schedule;
import com.demo.demo.vo.ScheduleVO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ScheduleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Schedule record);

    int insertSelective(Schedule record);

    Schedule selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Schedule record);

    int updateByPrimaryKey(Schedule record);

    List <Schedule> findByDateAndID(@Param("id") Integer id);

    int deleteByTodoAndId(Schedule record);
}