package com.demo.demo.service.impl;

import com.demo.demo.common.ResponseCode;
import com.demo.demo.dao.DailyMapper;
import com.demo.demo.pojo.Daily;
import com.demo.demo.pojo.User;
import com.demo.demo.service.IDailyservice;
import com.demo.demo.utils.ServerResponse;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.demo.demo.utils.DateUtils;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class Dailyservice implements IDailyservice {

    @Autowired
    DailyMapper dailyMapper;

    @Override
    public ServerResponse Insertlogic(Daily daily,String date1) {

        daily.setDate(DateUtils.string2Date(date1));
        Daily daily1 = dailyMapper.findByIdAndDate(daily.getId(),date1);
        Integer result;

        if(daily1 == null)
        {
            result = dailyMapper.insertSelective(daily);
        }
        else{
            result = dailyMapper.updateByPrimaryKeySelective(daily);
        }
        if(result == 0)
        {
            return ServerResponse.createServerResponseByfail(ResponseCode.INSERT_FAILED.getCode(),ResponseCode.INSERT_FAILED.getMsg());
        }
        return ServerResponse.createServerResponseBySuccess();

    }

    @Override
    public ServerResponse Searchlogic(String date, Integer id) {
        Daily daily = dailyMapper.findByIdAndDate(id,date);
        return ServerResponse.createServerResponseBySuccess(daily);
    }

    @Override
    public ServerResponse Search_30_logic(Integer id) {
        List<Daily> result = dailyMapper.findMonthdate(id);
        return ServerResponse.createServerResponseBySuccess(result);
    }

    @Override
    public ServerResponse Search_14_logic(Integer id) {
        List<Daily> result = dailyMapper.find14days(id);
        return ServerResponse.createServerResponseBySuccess(result);
    }

    @Override
    public ServerResponse Search_7_logic(Integer id) {
        List<Daily> result = dailyMapper.find7days(id);
        return ServerResponse.createServerResponseBySuccess(result);
    }

    @Override
    public ServerResponse Search_3_logic(Integer id) {
        List<Daily> result = dailyMapper.find3days(id);
        return ServerResponse.createServerResponseBySuccess(result);
    }


}
