package com.project.campustaobao.server.impl;


import com.project.campustaobao.pojo.ShoppingCartGoods;
import com.project.campustaobao.pojo.User;
import com.project.campustaobao.mapper.UserMapper;
import com.project.campustaobao.pojo.VIPUser;
import com.project.campustaobao.server.ShoppingCartServer;
import com.project.campustaobao.server.UserServer;
import com.project.campustaobao.utils.DateUtils;
import com.project.campustaobao.utils.RegisterUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Map;

//实现服务
@Service
public class UserServerImpl implements UserServer {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ShoppingCartServer shoppingCartServer;
    /**
     * 判断账号是否被注册过
     * @param account 传入的账号
     * @return 账号被注册过就返回true
     */
    @Override
    public boolean isRegistered(String account) {
        String queryAccount = userMapper.queryAccountByAccount(account);
        return queryAccount != null;
    }

    /**
     * 验证用户是否可以登录
     * @param account 账号
     * @param password 密码
     * @return 验证通过返回true
     */
    @Override
    public boolean login(String account, String password) {
        Map<String,String> map = userMapper.login(account,password);
        return map != null;
    }

    @Override
    public boolean register(String password, String tel){
        //注册账号之前首先检查账号是否存在
        String account = RegisterUser.generateAccount(tel);
        //如果用户已经注册过就返回false
        if(isRegistered(account)){
            return false;
        }else{
            Date date = new Date();
            String registerDate = DateUtils.formatDate(date);
            return userMapper.register(account,password,tel,registerDate);
        }

    }

    @Override
    public VIPUser queryVIPUserByAccount(String vipAccount) {
        return userMapper.queryVIPUserByAccount(vipAccount);
    }

    @Override
    public List<ShoppingCartGoods> queryAllShoppingCartGoods(String account) {
        return shoppingCartServer.queryAllShoppingCartGoods(account);
    }

    @Override
    public Map<String,String> queryUserInfoByAccount(String account) {
        return userMapper.queryUserInfoByAccount(account);
    }
}