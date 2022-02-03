package com.demo.demo.controller;


import com.demo.demo.common.Const;
import com.demo.demo.common.ResponseCode;
import com.demo.demo.pojo.Schedule;
import com.demo.demo.pojo.User;
import com.demo.demo.service.IScheduleservice;
import com.demo.demo.utils.ServerResponse;
import com.demo.demo.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;


//deal with the request of schedule table
@RestController
public class ScheduleController {

    @Autowired
    IScheduleservice iScheduleservice;
    //insert schedule data to schedule table
    @RequestMapping(value="/schedule/insert.do")
    public ServerResponse Insert(Schedule schedule)
    {
        return iScheduleservice.InsertLogic(schedule);
    }

    //search schedule data to schedule table by id
    @RequestMapping(value="/schedule/search.do")
    public ServerResponse Search(Integer id)
    {
        return iScheduleservice.SearchLogic(id);
    }

    //delete schedule data to schedule table by id and todo
    @RequestMapping(value="/schedule/delete.do")
    public ServerResponse Delete(Schedule schedule)
    {

        return iScheduleservice.DeleteLogic(schedule);
    }

    //this is not used in our front end
    //update schedule data to schedule table by id
    @RequestMapping(value="/schedule/update.do")
    public ServerResponse Update(Schedule schedule)
    {
        return iScheduleservice.UpdateLogic(schedule);
    }
}
