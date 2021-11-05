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


        //step2:check username is already exist;
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

        //step4: return result;
        //return null;
        return ServerResponse.createServerResponseBySuccess(convert(user));
    }

    public ServerResponse RegisterLogic(User user){
        //parameter can not be null;
        if(user == null)
        {
            return ServerResponse.createServerResponseByfail(ResponseCode.PARAMETER_NOT_EMPTY.getCode(),ResponseCode.PARAMETER_NOT_EMPTY.getMsg());
        }

        //age can not be null;
        if(user.getAge() == null||user.getAge().equals(""))
        {
            return ServerResponse.createServerResponseByfail(ResponseCode.AGE_NOT_EMPTY.getCode(),ResponseCode.AGE_NOT_EMPTY.getMsg());
        }

        //phone of doctor can not be null;
        if(user.getDocphone() == null||user.getDocphone().equals(""))
        {
            return ServerResponse.createServerResponseByfail(ResponseCode.DOC_PHONE_NOT_EMPTY.getCode(),ResponseCode.DOC_PHONE_NOT_EMPTY.getMsg());
        }

        //e-mail can not be null;
        if(user.getEmail() == null||user.getEmail().equals(""))
        {
            return ServerResponse.createServerResponseByfail(ResponseCode.E_MAIL_NOT_NULL.getCode(),ResponseCode.E_MAIL_NOT_NULL.getMsg());
        }

        //gender can not be null
        if(user.getGender() == null||user.getGender().equals(""))
        {
            return ServerResponse.createServerResponseByfail(ResponseCode.GENDER_NOT_NULL.getCode(),ResponseCode.GENDER_NOT_NULL.getMsg());
        }

        //username can not be null
        if(user.getName() == null||user.getName().equals(""))
        {
            return ServerResponse.createServerResponseByfail(ResponseCode.USERNAME_NOT_EMPTY.getCode(),ResponseCode.USERNAME_NOT_EMPTY.getMsg());
        }

        //password can not be null
        if(user.getPassword() == null||user.getPassword().equals(""))
        {
            return ServerResponse.createServerResponseByfail(ResponseCode.PASSWORD_NOT_EMPTY.getCode(),ResponseCode.PASSWORD_NOT_EMPTY.getMsg());
        }

        //phone can not be null
        if(user.getPhone() == null||user.getPhone().equals(""))
        {
            return ServerResponse.createServerResponseByfail(ResponseCode.PHONE_NOT_NULL.getCode(),ResponseCode.PHONE_NOT_NULL.getMsg());
        }

        //check name already exist
        Integer username_count = userMapper.findByUsername(user.getName());
        if(username_count > 0)
        {
            return ServerResponse.createServerResponseByfail(ResponseCode.USERNAME_ALREADY_EXIST.getCode(),ResponseCode.USERNAME_ALREADY_EXIST.getMsg());
        }

        //check email already exist
        Integer email_count = userMapper.findByEmail(user.getEmail());
        if(email_count > 0)
        {
            return ServerResponse.createServerResponseByfail(ResponseCode.USERNAME_ALREADY_EXIST.getCode(),ResponseCode.USERNAME_ALREADY_EXIST.getMsg());
        }

        //insert data to database;
        Integer result = userMapper.insert(user);
        if(result == 0)
        {
            return ServerResponse.createServerResponseByfail(ResponseCode.USERNAME_ALREADY_EXIST.getCode(),ResponseCode.USERNAME_ALREADY_EXIST.getMsg());
        }

        return ServerResponse.createServerResponseBySuccess();
    }
}
