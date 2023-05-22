package com.example.sqldemo.server.impl;
import com.example.sqldemo.dao.UserMapper;
import com.example.sqldemo.entity.User;
import com.example.sqldemo.server.UserServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
//实现服务
@Service
public class UserServerImpl implements UserServer {
    @Autowired
    private UserMapper userMapper;
    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public boolean checkLogin(String account,String password) {
        return password.equals(userMapper.getPassword(account));
    }

    @Override
    public boolean register(String account, String password, String tel) {
        return userMapper.register(account,password,tel);
    }
}