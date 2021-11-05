package com.demo.demo.controller;


import com.demo.demo.service.IDailyservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Daily {

    @Autowired
    IDailyservice iDailyservice;

}

