package com.project.campustaobao.server;

import com.project.campustaobao.pojo.DeliveryAddress;

import java.util.List;
import java.util.Map;

public interface DeliveryAddressServer {
    boolean isExist(DeliveryAddress address);
    /**
     * 用户添加一个收货地址
     * @param account 用户账号
     * @param consigneeName 收货人姓名
     * @param consigneeTel 收货人电话
     * @param address 收货地址
     * @return
     */
    DeliveryAddress insertDeliveryAddress(String account, String consigneeName, String consigneeTel,
                                          String address);

    /**
     * 查询用户所有的收货地址
     * @param account 用户账号
     * @return 返回该账号的用户的所有收货地址
     */
    List<DeliveryAddress> getDeliveryAddressList(String account);

    boolean deleteDeliveryAddress(String account,String addressNo);

    boolean updateDeliveryAddress(DeliveryAddress address);

}
