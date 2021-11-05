package com.demo.demo.controller;


import com.demo.demo.pojo.Schedule;
import com.demo.demo.service.IScheduleservice;
import com.demo.demo.utils.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class ScheduleController {

    @Autowired
    IScheduleservice iScheduleservice;

    @RequestMapping(value="/schedule/insert.do")
    public ServerResponse Insert(Schedule schedule)
    {
        return iScheduleservice.InsertLogic(schedule);
    }

    @RequestMapping(value="/schedule/search.do")
    public ServerResponse Search(String date)
    {
        return iScheduleservice.SearchLogic(date);
    }

    @RequestMapping(value="/schedule/delete.do")
    public ServerResponse Delete(Schedule schedule)
    {
        return iScheduleservice.DeleteLogic(schedule);
    }

    @RequestMapping(value="/schedule/update.do")
    public ServerResponse Update(Schedule schedule)
    {
        return iScheduleservice.UpdateLogic(schedule);
    }
}
