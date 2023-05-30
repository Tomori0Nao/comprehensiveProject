package com.project.campustaobao.server.impl;

import com.project.campustaobao.mapper.DeliveryAddressMapper;
import com.project.campustaobao.pojo.DeliveryAddress;
import com.project.campustaobao.server.DeliveryAddressServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryAddressServerImpl implements DeliveryAddressServer {
    @Autowired
    private DeliveryAddressMapper addressMapper;
    private static final String ADDRESS_PREFIX = "addr";

    @Override
    public boolean isExist(DeliveryAddress address) {
        return addressMapper.queryAddressNoByAddressInfo(address) != null;
    }
    @Override
    public DeliveryAddress insertDeliveryAddress(String account, String consigneeName, String consigneeTel, String address) {
        DeliveryAddress addr = new DeliveryAddress(null,account,consigneeName,
                consigneeTel,address);
        //若此地址已经存在就不需要再次添加了
        if(isExist(addr)){
            return null;
        }
        int count = addressMapper.getDeliveryAddressCount(account);
        addr = new DeliveryAddress(ADDRESS_PREFIX+account+count,
                account, consigneeName,consigneeTel,address);
        if(addressMapper.insertDeliveryAddress(addr)){
            return addr;
        }else{
            return null;
        }
    }
    public List<DeliveryAddress> getDeliveryAddressList(String account){
        return addressMapper.getDeliveryAddressList(account);
    }

    @Override
    public boolean deleteDeliveryAddress(String account, String addressNo) {
        return addressMapper.deleteDeliveryAddress(account,addressNo);
    }

    /**
     * 更新收货地址
     * @param address 用户新的收货地址信息
     * @return 更新成功返回true
     */
    @Override
    public boolean updateDeliveryAddress(DeliveryAddress address) {
        /*
        首先更新之前检查之前是否存在此收获地址
            如果不存在，就更新收获地址，但是注意更新后收货地址的编号不变
            如果存在，就无需更改，返回false
         */
        if(isExist(address)){
            return false;
        }else{
            return addressMapper.updateDeliveryAddress(address);
        }
    }
}
