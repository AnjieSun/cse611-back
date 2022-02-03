package com.demo.demo.service;

import com.demo.demo.pojo.Daily;
import com.demo.demo.pojo.User;
import com.demo.demo.utils.ServerResponse;

// this is an interface of daily service
public interface IDailyservice {


    public ServerResponse Insertlogic(Daily daily,String date1);

    public ServerResponse Searchlogic(String date, Integer id);

    public ServerResponse Search_30_logic(Integer id);

    public ServerResponse Search_14_logic(Integer id);

    public ServerResponse Search_7_logic(Integer id);

    public ServerResponse Search_3_logic(Integer id);


}
