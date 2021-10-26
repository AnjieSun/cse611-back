package com.demo.demo.service.impl;

import com.demo.demo.common.ResponseCode;
import com.demo.demo.dao.UserMapper;
import com.demo.demo.pojo.User;
import com.demo.demo.service.IUserservice;
import com.demo.demo.utils.ServerResponse;
import com.demo.demo.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class userservice implements IUserservice {

    @Autowired
    UserMapper userMapper;

    private UserVO convert(User user){
        UserVO userVo = new UserVO();
        userVo.setAge(user.getAge());
        userVo.setDocphone(user.getDocphone());
        userVo.setEmail(user.getEmail());
        userVo.setName(user.getName());
        userVo.setId(user.getId());
        userVo.setGender(user.getGender());
        return userVo;
    }

    @Override
    public ServerResponse loginLogic(String name, String password) {
        // step1: username and password not null;

        if(name == null || name.equals(""))
        {
            return ServerResponse.createServerResponseByfail(ResponseCode.USERNAME_NOT_EMPTY.getCode(),ResponseCode.USERNAME_NOT_EMPTY.getMsg());
        }

        if(password == null || password.equals(""))
        {
            return ServerResponse.createServerResponseByfail(ResponseCode.PASSWORD_NOT_EMPTY.getCode(),ResponseCode.PASSWORD_NOT_EMPTY.getMsg());
        }


        //step2:check username is exist;
        Integer count = userMapper.findByUsername(name);
        if(count == 0)
        {
            return ServerResponse.createServerResponseByfail(ResponseCode.USERNAME_NOT_EXIST.getCode(),ResponseCode.USERNAME_NOT_EXIST.getMsg());
        }

        //step3: search by username and password.
        User user = userMapper.findByUsernameAndPassword(name, password);
        if(user == null)
        {
            return ServerResponse.createServerResponseByfail(ResponseCode.PASSWORD_ERROR.getCode(), ResponseCode.PASSWORD_ERROR.getMsg());
        }

        //step4: return result、

        //return null;
        return ServerResponse.createServerResponseBySuccess(convert(user));
    }

    public ServerResponse RegisterLogic(User user){
        if(user == null){
            return ServerResponse.createServerResponseByfail(ResponseCode.PARAMETER_NOT_EMPTY.getCode(),ResponseCode.PARAMETER_NOT_EMPTY.getMsg());
        }

        return null;
    }
}
