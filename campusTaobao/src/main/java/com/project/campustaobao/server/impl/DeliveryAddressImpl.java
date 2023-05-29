package com.project.campustaobao.server.impl;

import com.project.campustaobao.mapper.DeliveryAddressMapper;
import com.project.campustaobao.pojo.DeliveryAddress;
import com.project.campustaobao.server.DeliveryAddressServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryAddressImpl implements DeliveryAddressServer {
    @Autowired
    private DeliveryAddressMapper addressMapper;
    private static final String ADDRESS_PREFIX = "addr";
    @Override
    public DeliveryAddress insertDeliveryAddress(String account, String consigneeName, String consigneeTel, String address) {
        int count = addressMapper.getDeliveryAddressCount(account);
        DeliveryAddress addr = new DeliveryAddress(ADDRESS_PREFIX+account+count,
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
}
