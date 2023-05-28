package com.project.campustaobao.server;

import com.project.campustaobao.pojo.ShoppingCartGoods;
import com.project.campustaobao.pojo.User;
import com.project.campustaobao.pojo.VIPUser;

import java.util.List;
import java.util.Map;

//服务层接口 对接的是 controller
public interface UserServer {
     boolean isRegistered(String account);
     boolean login(String account,String password);
     boolean isVIPUser(String account);
     boolean register(String password,String tel);
     VIPUser queryVIPUserByAccount(String vipAccount);
     List<ShoppingCartGoods> queryAllShoppingCartGoods(String account);

     Map<String,String> queryUserInfoByAccount(String account);

}