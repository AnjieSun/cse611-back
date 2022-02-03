package com.demo.demo.service.impl;

import com.demo.demo.common.ResponseCode;
import com.demo.demo.dao.UserMapper;
import com.demo.demo.pojo.User;
import com.demo.demo.service.IUserservice;
import com.demo.demo.utils.ServerResponse;
import com.demo.demo.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.demo.demo.utils.MD5Utils;

@Service
public class userservice implements IUserservice {

    @Autowired
    UserMapper userMapper;

    private UserVO convert(User user) {
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

        if (name == null || name.equals("")) {
            return ServerResponse.createServerResponseByfail(ResponseCode.USERNAME_NOT_EMPTY.getCode(), ResponseCode.USERNAME_NOT_EMPTY.getMsg());
        }

        if (password == null || password.equals("")) {
            return ServerResponse.createServerResponseByfail(ResponseCode.PASSWORD_NOT_EMPTY.getCode(), ResponseCode.PASSWORD_NOT_EMPTY.getMsg());
        }


        //step2:check username is already exist;
        Integer count = userMapper.findByUsername(name);
        if (count == 0) {
            return ServerResponse.createServerResponseByfail(ResponseCode.USERNAME_NOT_EXIST.getCode(), ResponseCode.USERNAME_NOT_EXIST.getMsg());
        }

        //step3: search by username and password.
        User user = userMapper.findByUsernameAndPassword(name, password);
        if (user == null) {
            return ServerResponse.createServerResponseByfail(ResponseCode.PASSWORD_ERROR.getCode(), ResponseCode.PASSWORD_ERROR.getMsg());
        }

        //step4: return result;
        //return null;
        return ServerResponse.createServerResponseBySuccess(user);
    }

    public ServerResponse RegisterLogic(User user) {
        //parameter can not be null;
        if (user == null) {
            return ServerResponse.createServerResponseByfail(ResponseCode.PARAMETER_NOT_EMPTY.getCode(), ResponseCode.PARAMETER_NOT_EMPTY.getMsg());
        }

        //age can not be null;
        if (user.getAge() == null || user.getAge().equals("")) {
            return ServerResponse.createServerResponseByfail(ResponseCode.AGE_NOT_EMPTY.getCode(), ResponseCode.AGE_NOT_EMPTY.getMsg());
        }

        //phone of doctor can not be null;
        if (user.getDocemail() == null || user.getDocemail().equals("")) {
            return ServerResponse.createServerResponseByfail(ResponseCode.DOC_EMAIL_NOT_EMPTY.getCode(), ResponseCode.DOC_EMAIL_NOT_EMPTY.getMsg());
        }


        //gender can not be null
        if (user.getGender() == null || user.getGender().equals("")) {
            return ServerResponse.createServerResponseByfail(ResponseCode.GENDER_NOT_NULL.getCode(), ResponseCode.GENDER_NOT_NULL.getMsg());
        }

        //username can not be null
        if (user.getName() == null || user.getName().equals("")) {
            return ServerResponse.createServerResponseByfail(ResponseCode.USERNAME_NOT_EMPTY.getCode(), ResponseCode.USERNAME_NOT_EMPTY.getMsg());
        }

        //password can not be null and encode password
        if (user.getPassword() == null || user.getPassword().equals("")) {

            return ServerResponse.createServerResponseByfail(ResponseCode.PASSWORD_NOT_EMPTY.getCode(), ResponseCode.PASSWORD_NOT_EMPTY.getMsg());
        } else {
            user.setPassword(MD5Utils.MD5Encode(user.getPassword(), "utf8"));
        }

        //phone can not be null
        if (user.getPhone() == null || user.getPhone().equals("")) {
            return ServerResponse.createServerResponseByfail(ResponseCode.PHONE_NOT_NULL.getCode(), ResponseCode.PHONE_NOT_NULL.getMsg());
        }

        //check the format of email
        if (!user.getDocemail().matches("^[a-z0-9A-Z]+[- | a-z0-9A-Z . _]+@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-z]{2,}$")) {
            return ServerResponse.createServerResponseByfail(ResponseCode.WRONG_EMAIL.getCode(), ResponseCode.WRONG_EMAIL.getMsg());
        }


        //check name already exist
        Integer username_count = userMapper.findByUsername(user.getName());
        if (username_count > 0) {
            return ServerResponse.createServerResponseByfail(ResponseCode.USERNAME_ALREADY_EXIST.getCode(), ResponseCode.USERNAME_ALREADY_EXIST.getMsg());
        }


        //insert data to database;
        Integer result = userMapper.insert(user);
        if (result == 0) {
            return ServerResponse.createServerResponseByfail(ResponseCode.INSERT_FAILED.getCode(), ResponseCode.INSERT_FAILED.getMsg());
        }

        return ServerResponse.createServerResponseBySuccess();
    }

    @Override
    public ServerResponse Searchlogic(Integer id) {

        User user = userMapper.selectByPrimaryKey(id);
        if (user == null) {
            return ServerResponse.createServerResponseByfail(ResponseCode.SEARCH_FAILED.getCode(), ResponseCode.SEARCH_FAILED.getMsg());
        }
        return ServerResponse.createServerResponseBySuccess(user);
    }

    @Override
    public ServerResponse Updatelogic(User user) {
        Integer count1 = userMapper.findByUsername(user.getName());
        if (count1 == 0) {
            Integer count = userMapper.updateByPrimaryKeySelective(user);
            if (count == 0) {
                ServerResponse.createServerResponseByfail(ResponseCode.UPDATE_FAILED.getCode(), ResponseCode.UPDATE_FAILED.getMsg());
            }
            User user1 = userMapper.selectByPrimaryKey(user.getId());
            return ServerResponse.createServerResponseBySuccess(user1);
        } else {
            return ServerResponse.createServerResponseByfail(ResponseCode.USERNAME_ALREADY_EXIST.getCode(), ResponseCode.USERNAME_ALREADY_EXIST.getMsg());
        }
    }
}
