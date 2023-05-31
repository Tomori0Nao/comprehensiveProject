package com.project.campustaobao.server.impl;


import com.project.campustaobao.mapper.GoodsMapper;
import com.project.campustaobao.mapper.VIPUserMapper;
import com.project.campustaobao.pojo.ShoppingCartGoods;
import com.project.campustaobao.mapper.UserMapper;
import com.project.campustaobao.pojo.User;
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
    private GoodsMapper goodsMapper;
    @Autowired
    private VIPUserMapper vipUserMapper;
    @Autowired
    private ShoppingCartServer shoppingCartServer;

    /**
     * 判断用户是否被禁用
     * @param account
     * @return 该用户禁用则返回true
     */
    @Override
    public boolean isBaned(String account) {
        return userMapper.isBaned(account);
    }

    @Override
    public boolean updateUserStatus(String account, boolean status) {
        return userMapper.updateUserStatus(account,status);
    }

    @Override
    public boolean forbidUser(String account){
        return updateUserStatus(account,true);
    }
    @Override
    public boolean notForbidUser(String account){
        return updateUserStatus(account,false);
    }
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
    public int login(String account, String password) {
        if(userMapper.isBaned(account)){
            return 1;
        }else if(userMapper.login(account,password) !=null){
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime time = LocalDateTime.now();
            String localDateTime = df.format(time);
            LocalDateTime ldt = LocalDateTime.parse(localDateTime, df);
            System.out.println(ldt);
            userMapper.updateLoginTime(account,ldt.toString());
            return 0;
        } else{
            return 2;
        }
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

    @Override
    public Map<String,String> queryUserSimpleInfoByAccount(String userAccount) {
        return userMapper.queryUserSimpleInfoByAccount(userAccount);
    }

    @Override
    public List<Map<String, String>> queryUsers() {
        return userMapper.queryUsers();
    }

    @Override
    public boolean deleteCartGoods(String account, String goodsNo) {
        return userMapper.deleteCartGoods(account, goodsNo);
    }

    @Override
    public boolean insertGoodsToCart(String account, String goodsNo, int goodsNumber) {
        double price = Double.parseDouble(goodsMapper.queryGoodsPriceByGoodsNo(goodsNo));
        if(isExistInCart(account, goodsNo)){
            int oldNumber = userMapper.queryCartGoodsNumber(account, goodsNo);
            String totalCost = price * (oldNumber + goodsNumber) +"";
            userMapper.updateGoodsNumberInCart(account,goodsNo,goodsNumber,totalCost);
            return true;
        }else{

            String totalCost = price * goodsNumber + "";
            return userMapper.insertGoodsToCart(account, goodsNo, goodsNumber,totalCost);
        }
    }

    @Override
    public boolean isExistInCart(String account, String goodsNo) {
        return userMapper.isExistInCart(account, goodsNo) != null;
    }
}