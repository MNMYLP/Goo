package com.example.springsecurity.controller;

import com.example.springsecurity.enitiy.ResponseResult;
import com.example.springsecurity.enitiy.User;
import com.example.springsecurity.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class UserController {
//    @Autowired
//    private UserMapper userMapper;
//    @RequestMapping("hello")
//    @PreAuthorize("hasAuthority('admin')")
//    public String hello(){
//        return "hello";
//    }

//    @RequestMapping("testCors")
//    @PreAuthorize("hasAuthority('admin')")
//    public ResponseResult testCors(){
//        return new ResponseResult(200,"testCors");
//    }
}
