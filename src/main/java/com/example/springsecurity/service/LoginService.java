package com.example.springsecurity.service;

import com.example.springsecurity.enitiy.ResponseResult;
import com.example.springsecurity.enitiy.User;

public interface LoginService {
    public ResponseResult login(User user);
    ResponseResult logout();
}
