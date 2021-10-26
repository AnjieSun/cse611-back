package com.demo.demo.controller;

import com.demo.demo.common.Const;
import com.demo.demo.service.IUserservice;
import com.demo.demo.utils.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * 注意@RestController用于写接口
 * @Controller 用于写页面跳转
 */
@RestController
public class UserController {

    @Autowired
    IUserservice userservice;


    @RequestMapping(value="/user/login.do")
    public  ServerResponse login(String username, String password, HttpSession session){
        ServerResponse serverResponse = userservice.loginLogic(username, password);
        if(serverResponse.isSuccess())
        {
            session.setAttribute(Const.CURRENT_USER,serverResponse.getData());
        }
        return serverResponse;
    }
}


