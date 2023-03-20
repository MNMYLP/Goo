package com.example.springsecurity.service.Impl;

import com.example.springsecurity.enitiy.LoginUser;
import com.example.springsecurity.enitiy.ResponseResult;
import com.example.springsecurity.enitiy.User;
import com.example.springsecurity.service.LoginService;
import com.example.springsecurity.utils.JwtUtil;
import com.example.springsecurity.utils.RedisCache;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisCache redisCache;
    @Override
    public ResponseResult login(User user) {
        System.out.println(user);
        //获取AuthenticationManager进行用户认证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        System.out.println(Objects.isNull(authenticate));
        //如果验证没通过给出提示
        if(Objects.isNull(authenticate)){
            throw new RuntimeException("用户名或密码错误");
        }
        //使用userid生成token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        //authenticate存入redis
        redisCache.setCacheObject("login:"+userId,loginUser);
        //把token响应给前端
        HashMap<String,String> map = new HashMap<>();
        map.put("token",jwt);
        return new ResponseResult(200,"登陆成功",map);
        //如果认证通过,用用户id生成jwt,存入responseResult返回
        //把完整的用户信息存入redis userid作为key
    }

    @Override
    public ResponseResult logout() {
        //获取用户ID
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser principal = (LoginUser) authentication.getPrincipal();
        Long id = principal.getUser().getId();
        //删除redis中的缓存数据
        redisCache.deleteObject("login:"+id);
        return new ResponseResult(200,"注销成功");
    }
}
