package com.example.springsecurity;

import com.example.springsecurity.enitiy.User;
import com.example.springsecurity.mapper.MenuMapper;
import com.example.springsecurity.mapper.UserMapper;
import com.mysql.cj.log.Log;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class SpringSecurityApplicationTests {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MenuMapper mapper;
    @Test
    public void test(){
//        List<String> list = mapper.selectPermsByUserId(1L);
//        list.forEach(System.out::println);
//        selectPermsByUserId
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode("123");
////        String password1 = passwordEncoder.encode("123");
////        System.out.println(password1);
        System.out.println(password);
//        boolean matches = passwordEncoder.matches("123", "$2a$10$qJcgLqE9iCwrlg3tlcf/XeCvUEnTo1Lto4V7JSAHwGnB1ouYRBtG6");
//        System.out.println(matches);
    }
    @Test
    void contextLoads() {
        userMapper.selectList(null).forEach(System.out::println);
//        List<User> users = userMapper.selectList(null);
//        users.stream().forEach(item-> System.out.println(item));
    }

}
