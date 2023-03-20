package com.example.springsecurity.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springsecurity.enitiy.LoginUser;
import com.example.springsecurity.enitiy.ResponseResult;
import com.example.springsecurity.enitiy.User;
import com.example.springsecurity.mapper.UserMapper;
import com.example.springsecurity.service.LoginService;
import com.example.springsecurity.utils.JwtUtil;
import com.example.springsecurity.utils.RedisCache;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.invoke.VarHandle;

@RestController
//@RequestMapping("/user")
public class LoginController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private LoginService loginService;
    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user){
//        System.out.println(user);
        //登录
        return loginService.login(user);
    }
    @RequestMapping("/user/logout")
    @PreAuthorize("hasAnyAuthority('admin','test')")
    public ResponseResult logout(){
        return loginService.logout();
    }
    @RequestMapping("/user/userinfo")
    @PreAuthorize("hasAnyAuthority('admin','test')")
    public ResponseResult userinfo(HttpServletRequest request){
        String token = request.getHeader("token");
        //解析token
        String userid;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userid = claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("token非法");
        }
        //从redis中获取用户信息
        String redisKey = "login:" + userid;
        LoginUser loginUser = redisCache.getCacheObject(redisKey);
        return new ResponseResult(200,"用户信息获取成功", loginUser);
    }

}
