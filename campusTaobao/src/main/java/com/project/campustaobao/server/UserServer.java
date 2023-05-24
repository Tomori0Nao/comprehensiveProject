package com.project.campustaobao.server;

import com.project.campustaobao.pojo.User;
import com.project.campustaobao.pojo.VIPUser;

import java.util.List;

//服务层接口 对接的是 controller
public interface UserServer {
     boolean isRegistered(String account);
     boolean login(String account,String password);

     boolean register(String password,String tel);
     VIPUser queryVIPUserByAccount(String vipAccount);
}