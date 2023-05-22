package com.example.sqldemo.dao;


import com.example.sqldemo.entity.User;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
//这是对接 MyBatis 连接数据库的的XML 文件的层次
@Repository
public interface UserMapper {
    List<User> findAll();
    /**
     * 返回String字符串
     * @param account 输入账号
     * @return 数据库查到的密码
     */
    String getPassword(@Param("user_account") String account);

    boolean register(@Param("user_account") String account ,@Param("user_password") String password,@Param("user_tel") String tel);
}

