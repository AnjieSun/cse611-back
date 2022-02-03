package com.demo.demo.controller;


import com.demo.demo.pojo.Daily;
import com.demo.demo.service.IDailyservice;
import com.demo.demo.utils.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//deal with the request of daily table
@RestController
public class DailyController {

    @Autowired
    IDailyservice iDailyservice;
    //insert page
    @RequestMapping(value="/daily/insert.do")
    public ServerResponse Insert(Daily daily,String date1)
    {

        return iDailyservice.Insertlogic(daily,date1);
    }
    //search by id page
    @RequestMapping(value="/daily/search.do")
    public ServerResponse Search(String date, Integer id){
        return iDailyservice.Searchlogic(date , id);
    }

    //search 30 days data
    @RequestMapping(value="/daily/search30.do")
    public ServerResponse Search30(Integer id){
        return iDailyservice.Search_30_logic(id);
    }

    //search 14 days data
    @RequestMapping(value="/daily/search14.do")
    public ServerResponse Search14(Integer id){
        return iDailyservice.Search_14_logic(id);
    }

    //search 7 days data
    @RequestMapping(value="/daily/search7.do")
    public ServerResponse Search7(Integer id){
        return iDailyservice.Search_7_logic(id);
    }

    //search 3 days data
    @RequestMapping(value="/daily/search3.do")
    public ServerResponse Search3(Integer id){
        return iDailyservice.Search_3_logic(id);
    }


}

