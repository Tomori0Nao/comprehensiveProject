package com.project.campustaobao.server;

import com.project.campustaobao.pojo.ShoppingCartGoods;
import com.project.campustaobao.pojo.User;
import com.project.campustaobao.pojo.VIPUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

//服务层接口 对接的是 controller
public interface UserServer {
     boolean isBaned(String account);
     boolean updateUserStatus(String account,boolean status);
     boolean forbidUser(String account);
     boolean notForbidUser(String account);
     boolean isRegistered(String account);
     int login(String account,String password);
     boolean isVIPUser(String account);

     /**
      * 注册一个账号，并插入到数据库
      * @param password 用户注册时的密码
      * @param tel 用户注册时填写的电话号码
      * @return 插入成功即注册成功返回false
      */
     boolean register(String password,String name,String tel);
     VIPUser queryVIPUserByAccount(String vipAccount);
     List<ShoppingCartGoods> queryAllShoppingCartGoods(String account);


     Map<String,String> queryUserInfoByAccount(String account);

     boolean updateUserNameByAccount(String account,String newName);

     Map<String,String> queryUserSimpleInfoByAccount(String userAccount);
     List<Map<String,String>> queryUsers();

    boolean deleteCartGoods(String account, String goodsNo);
    boolean insertGoodsToCart(@Param("account")String account, @Param("goodsNo")String goodsNo, @Param("goodsNumber")int goodsNumber);
    boolean isExistInCart(@Param("account")String account,@Param("goodsNo")String goodsNo);
}