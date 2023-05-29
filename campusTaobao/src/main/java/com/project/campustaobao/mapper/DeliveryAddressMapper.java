package com.project.campustaobao.mapper;

import com.project.campustaobao.pojo.DeliveryAddress;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DeliveryAddressMapper {
    /**
     * 用户新增一个收货地址
     * @param deliveryAddress 收货地址
     * @return 插入成功返回true
     */
    boolean insertDeliveryAddress(@Param("addr") DeliveryAddress deliveryAddress);

    /**
     * 获取用户收获地址数目
     * @param account 用户账号
     * @return 返回该用户的收货地址数目
     */
    int getDeliveryAddressCount(@Param("account") String account);

    List<DeliveryAddress> getDeliveryAddressList(@Param("account") String account);
}
