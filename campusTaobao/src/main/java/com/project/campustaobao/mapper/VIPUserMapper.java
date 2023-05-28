package com.project.campustaobao.mapper;

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
    boolean queryVIPAccountByAccount(String account);
}
