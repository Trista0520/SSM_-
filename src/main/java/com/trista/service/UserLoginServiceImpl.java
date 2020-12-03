package com.trista.service;

import com.trista.mapper.UserLoginMapper;
import com.trista.pojo.UserLogin;

import java.util.List;

public class UserLoginServiceImpl implements UserLoginService {

    private UserLoginMapper userLoginMapper;

    public void setUserLoginMapper(UserLoginMapper userLoginMapper) {
        this.userLoginMapper = userLoginMapper;
    }

    public int addUserLogin(UserLogin userLogin) {
        return userLoginMapper.addUserLogin(userLogin);
    }

    public int deleteUserLoginById(int userID) {
        return userLoginMapper.deleteUserLoginById(userID);
    }

    public int updateUserLogin(UserLogin userLogin) {
        return userLoginMapper.updateUserLogin(userLogin);
    }

    public UserLogin queryUserLoginByName(String userName) {
        return userLoginMapper.queryUserLoginByName(userName);
    }

    public List<UserLogin> queryAllUserLogin() {
        return userLoginMapper.queryAllUserLogin();
    }
}
