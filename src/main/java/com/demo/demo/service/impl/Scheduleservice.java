package com.demo.demo.service.impl;

import com.demo.demo.common.ResponseCode;
import com.demo.demo.dao.ScheduleMapper;
import com.demo.demo.pojo.Schedule;
import com.demo.demo.pojo.User;
import com.demo.demo.service.IScheduleservice;
import com.demo.demo.utils.DateUtils;
import com.demo.demo.utils.ServerResponse;
import com.demo.demo.vo.ScheduleVO;
import com.demo.demo.vo.UserVO;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class Scheduleservice implements IScheduleservice {

    @Autowired
    ScheduleMapper scheduleMapper;

    private ScheduleVO convert(Schedule schedule){
        ScheduleVO scheduleVO = new ScheduleVO();
        scheduleVO.setId(schedule.getId());
        scheduleVO.setTodo(schedule.getTodo());
        scheduleVO.setDate(DateUtils.date2String(schedule.getDate()));
        return scheduleVO;
    }


    @Override
    public ServerResponse InsertLogic(Schedule schedule) {
//        Date date = new Date();//get system time
//        String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
//        Timestamp goodsC_date = Timestamp.valueOf(nowTime);
//        schedule.setDate(goodsC_date);

        if(schedule.getDate().equals("") || schedule.getDate()== null)
        {
            return ServerResponse.createServerResponseByfail(ResponseCode.DATE_CAN_NULL.getCode(),ResponseCode.DATE_CAN_NULL.getMsg());
        }


        Integer result = scheduleMapper.insert(schedule);
        if(result == 0)
        {
            return ServerResponse.createServerResponseByfail(ResponseCode.INSERT_FAILED.getCode(),ResponseCode.INSERT_FAILED.getMsg());
        }
        return ServerResponse.createServerResponseBySuccess();
    }

    @Override
    public ServerResponse SearchLogic(Integer id) {

        List <Schedule>  result = scheduleMapper.findByDateAndID(id);
        if(result == null||result.isEmpty())
        {
            return ServerResponse.createServerResponseByfail(ResponseCode.SEARCH_FAILED.getCode(),ResponseCode.SEARCH_FAILED.getMsg());
        }
        return ServerResponse.createServerResponseBySuccess(result);
    }

    @Override
    public ServerResponse DeleteLogic(Schedule schedule) {
        Integer result = scheduleMapper.deleteByTodoAndId(schedule);
        if(result == 0)
        {
            return ServerResponse.createServerResponseByfail(ResponseCode.DELETE_FAILED.getCode(),ResponseCode.DELETE_FAILED.getMsg());
        }
        return ServerResponse.createServerResponseBySuccess();
    }

    @Override
    public ServerResponse UpdateLogic(Schedule schedule) {
        Integer result = scheduleMapper.updateByPrimaryKeySelective(schedule);
        if(result == 0)
        {
            return ServerResponse.createServerResponseByfail(ResponseCode.UPDATE_FAILED.getCode(),ResponseCode.UPDATE_FAILED.getMsg());
        }
        return ServerResponse.createServerResponseBySuccess();
    }
}
