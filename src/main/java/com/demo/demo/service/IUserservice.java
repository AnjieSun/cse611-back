package com.demo.demo.service;

import com.demo.demo.pojo.User;
import com.demo.demo.utils.ServerResponse;

// this is an interface of user service
public interface IUserservice {

    public ServerResponse loginLogic(String name, String password);

    public ServerResponse RegisterLogic(User user);

    public ServerResponse Searchlogic(Integer id);

    public ServerResponse Updatelogic(User user);
}
