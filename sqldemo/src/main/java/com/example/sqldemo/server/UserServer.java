package com.example.sqldemo.server;

import com.example.sqldemo.entity.User;

import java.util.List;

//服务层接口 对接的是 controller
public interface UserServer {
     List<User> findAll();
     boolean checkLogin(String account,String password);

     boolean register(String account,String password,String tel);
}