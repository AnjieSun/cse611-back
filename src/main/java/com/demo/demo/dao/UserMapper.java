package com.demo.demo.dao;

import com.demo.demo.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);


//implementation

    int findByUsername(@Param("name")  String username);

    User findByUsernameAndPassword(@Param("name") String username,@Param("password") String password);

    int findByEmail(@Param("email")  String email);
}