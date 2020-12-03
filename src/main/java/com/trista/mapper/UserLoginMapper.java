package com.trista.mapper;

import com.trista.pojo.Teacher;
import com.trista.pojo.UserLogin;

import java.util.List;

public interface UserLoginMapper {

    //增加登录人员
    int addUserLogin(UserLogin userLogin);

    //根据id删除登录人员
    int deleteUserLoginById(int userID);

    //修改登录人员信息
    int updateUserLogin(UserLogin userLogin);

    //根据名称查询登录人员
    UserLogin queryUserLoginByName(String userName);

    //查询全部登录人员
    List<UserLogin> queryAllUserLogin();
}
