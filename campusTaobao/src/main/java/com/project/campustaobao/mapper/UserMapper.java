package com.project.campustaobao.mapper;
import com.project.campustaobao.pojo.User;
import com.project.campustaobao.pojo.VIPUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

//这是对接 MyBatis 连接数据库的的XML 文件的层次
@Mapper
@Repository
public interface UserMapper {
    /**
     * 查询用户禁用状态
     * @param account 用户账号
     * @return 返回该用户的禁用状态
     */
    boolean isBaned(@Param("account")String account);
    /**
     * 更新用户状态   正常or禁用
     * @param account
     * @return 更新成功返回true,否则返回false
     */
    boolean updateUserStatus(@Param("account")String account, @Param("status")boolean baned);


    Map<String,String> login(String account, String password);
    boolean updateLoginTime(@Param("account") String account,@Param("lastLoginTime") String time);
    boolean register(@Param("account") String account ,@Param("password") String password,@Param("name")String name ,@Param("tel") String tel,@Param("registerDate") String registerDate);
    String queryAccountByAccount(@Param("account") String account);
    Map<String,String> queryUserInfoByAccount(@Param("account") String account);
    Map<String,String> queryUserSimpleInfoByAccount(@Param("account")String account);
    /**
     * 修改用户昵称
     * @param account 用户账号
     * @param newName 用户新昵称
     * @return 成功修改则返回true
     */
    boolean updateUserNameByAccount(@Param("account") String account,@Param("newName")String newName);

    /**
     * 查找所有用户的部分信息
     * @return 所有用户的部分信息
     */
    List<Map<String,String>> queryUsers();
}

