package com.project.campustaobao.mapper;
import com.project.campustaobao.pojo.VIPUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

//这是对接 MyBatis 连接数据库的的XML 文件的层次
@Mapper
@Repository
public interface UserMapper {
    Map<String,String> login(String account, String password);
    boolean register(@Param("account") String account ,@Param("password") String password,@Param("name")String name ,@Param("tel") String tel,@Param("registerDate") String registerDate);
    String queryAccountByAccount(@Param("account") String account);
    Map<String,String> queryUserInfoByAccount(@Param("account") String account);
}

