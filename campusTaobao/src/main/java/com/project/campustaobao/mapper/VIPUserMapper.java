package com.project.campustaobao.mapper;

import com.project.campustaobao.pojo.VIPUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface VIPUserMapper {
    /**
     * 判断用户是否为VIP用户
     * @param account
     * @return 是vip用户则返回true
     */
    boolean isVIPUser(String account);

    VIPUser queryVIPUserByAccount(String vipAccount);
}
