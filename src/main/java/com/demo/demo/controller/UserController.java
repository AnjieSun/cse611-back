package com.demo.demo.controller;

import com.demo.demo.common.Const;
import com.demo.demo.pojo.User;
import com.demo.demo.service.IUserservice;
import com.demo.demo.utils.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @RestController used to implementation
 * @Controller used to jump to other page
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

    @RequestMapping(value="/user/register.do")
    public ServerResponse register(User user)
    {
        return userservice.RegisterLogic(user);
    }
}


