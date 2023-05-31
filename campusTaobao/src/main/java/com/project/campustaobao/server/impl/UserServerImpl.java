package com.project.campustaobao.server.impl;


import com.project.campustaobao.mapper.VIPUserMapper;
import com.project.campustaobao.pojo.ShoppingCartGoods;
import com.project.campustaobao.mapper.UserMapper;
import com.project.campustaobao.pojo.VIPUser;
import com.project.campustaobao.server.ShoppingCartServer;
import com.project.campustaobao.server.UserServer;
import com.project.campustaobao.utils.DateUtils;
import com.project.campustaobao.utils.RegisterUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;

//实现服务
@Service
public class UserServerImpl implements UserServer {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private VIPUserMapper vipUserMapper;
    @Autowired
    private ShoppingCartServer shoppingCartServer;
    /**
     * 判断账号是否被注册过
     * @param account 传入的账号
     * @return 账号被注册过就返回true
     */
    @Override
    public boolean isRegistered(String account) {
        String a = userMapper.queryAccountByAccount(account);
        return a != null;
    }

    /**
     * 验证用户是否可以登录
     * @param account 账号
     * @param password 密码
     * @return 验证通过返回true
     */
    @Override
    public boolean login(String account, String password) {
        boolean login = userMapper.login(account,password) != null;
        if(login){
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime time = LocalDateTime.now();
            String localDateTime = df.format(time);
            LocalDateTime ldt = LocalDateTime.parse(localDateTime, df);
            System.out.println(ldt);
            userMapper.updateLoginTime(account,ldt.toString());
        }
        return login;
    }

    /**
     * 判断是否是VIP用户
     * @param account 账号
     * @return 是VIP用户返回true,否则返回false
     */
    @Override
    public boolean isVIPUser(String account) {
        return vipUserMapper.isVIPUser(account);
    }

    @Override
    public boolean register(String password,String name,String tel){
        String account = RegisterUser.generateAccount(tel);
        /*
            注册账号之前首先检查账号是否存在
            如果用户已经注册过就返回false
        */
        if(isRegistered(account)){
            return false;
        }else{
            Date date = new Date();
            String registerDate = DateUtils.formatDate(date);
            return userMapper.register(account,password,name,tel,registerDate);
        }
    }
    @Override
    public VIPUser queryVIPUserByAccount(String vipAccount) {
        return vipUserMapper.queryVIPUserByAccount(vipAccount);
    }

    @Override
    public List<ShoppingCartGoods> queryAllShoppingCartGoods(String account) {
        return shoppingCartServer.queryAllShoppingCartGoods(account);
    }

    @Override
    public Map<String,String> queryUserInfoByAccount(String account) {
        return userMapper.queryUserInfoByAccount(account);
    }

    @Override
    public boolean updateUserNameByAccount(String account, String newName) {
        return userMapper.updateUserNameByAccount(account,newName);
    }
}