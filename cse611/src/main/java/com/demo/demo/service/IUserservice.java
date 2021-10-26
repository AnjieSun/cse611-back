package com.demo.demo.service;

import com.demo.demo.pojo.User;
import com.demo.demo.utils.ServerResponse;

public interface IUserservice {

    public ServerResponse loginLogic(String name, String password);

    public ServerResponse RegisterLogic(User user);
}
