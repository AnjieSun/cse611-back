package com.demo.demo.service;

import com.demo.demo.pojo.Schedule;
import com.demo.demo.utils.ServerResponse;

import java.util.Date;

public interface IScheduleservice {

    public ServerResponse InsertLogic(Schedule schedule);

    public ServerResponse SearchLogic(String date);

    public ServerResponse DeleteLogic(Schedule schedule);

    public ServerResponse UpdateLogic(Schedule schedule);
}
