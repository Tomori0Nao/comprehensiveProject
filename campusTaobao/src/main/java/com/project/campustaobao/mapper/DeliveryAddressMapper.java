package com.project.campustaobao.mapper;

import com.project.campustaobao.pojo.DeliveryAddress;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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
    String queryAddressNoByAddressInfo(@Param("addr") DeliveryAddress address);
    String queryAddressByAddressNo(@Param("addressNo") String addressNo);
    List<DeliveryAddress> getDeliveryAddressList(@Param("account") String account);
    boolean deleteDeliveryAddress(@Param("account") String account,@Param("addressNo") String addressNo);

    /**
     * 更新收货地址
     * @param address 收货地址信息
     * @return 更新成功返回true
     */
    boolean updateDeliveryAddress(@Param("addr")DeliveryAddress address);
}
