package com.demo.demo.service;

import com.demo.demo.pojo.Schedule;
import com.demo.demo.utils.ServerResponse;

import java.util.Date;

// this is an interface of schedule service
public interface IScheduleservice {

    public ServerResponse InsertLogic(Schedule schedule);

    public ServerResponse SearchLogic(Integer id);

    public ServerResponse DeleteLogic(Schedule schedule);

    public ServerResponse UpdateLogic(Schedule schedule);
}
