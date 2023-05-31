package com.project.campustaobao.server.impl;

import com.project.campustaobao.mapper.DeliveryAddressMapper;
import com.project.campustaobao.mapper.GoodsMapper;
import com.project.campustaobao.mapper.OrderMapper;
import com.project.campustaobao.pojo.Goods;
import com.project.campustaobao.pojo.UserOrder;
import com.project.campustaobao.server.OrderServer;
import com.project.campustaobao.utils.DateUtils;
import com.project.campustaobao.utils.Request;
import com.project.campustaobao.utils.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServerImpl implements OrderServer {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private DeliveryAddressMapper addressMapper;

    /**
     * 管理员查看订单
     * @return 所有订单的信息(部分信息)
     */
    @Override
    public List<Map<String, String>> queryAllOrders() {
        return orderMapper.queryAllOrders();
    }

    @Override
    public List<UserOrder> getOrderListByAccount(String account) {
        List<Map<String,Object>> orderList = orderMapper.queryOrderListByAccount(account);
        List<UserOrder> orders = new ArrayList<>(orderList.size());
        for(Map<String,Object> map:orderList){
            UserOrder order = new UserOrder();
            order.setOrderNo((String)map.get("order_no"));
            order.setUserAccount((String)Session.getSessionAttribute(Request.getRequest(),
                    "account"));
            LocalDateTime dateTime = (LocalDateTime)(map.get("order_time"));
            order.setOrderTime(DateUtils.formatDateTime(dateTime));
            order.setOrderDerate((String)map.get("order_derate"));
            order.setOrderPrice((String)map.get("order_price"));
            order.setActualPayment((String)map.get("actual_payment"));
            int goodsNum = (Integer)map.get("goods_num");
            order.setGoodsNumber(goodsNum);
            Goods goods = goodsMapper.queryGoodsByGoodsNo((String)map.get("goods_no"));
            order.setGoodsName(goods.getGoodsName());
            order.setGoodsImageName(goods.getGoodsImageName());
            order.setStoreName(goods.getStoreName());
            order.setDeliveryAddress(addressMapper.queryAddressByAddressNo((String)map.get("delivery_no")));
            orders.add(order);
        }
        return orders;
    }

    @Override
    public boolean deleteOrder(String orderNo) {
        return orderMapper.deleteOrderByOrderNo(orderNo);
    }

    @Override
    public Map<String, String> querySimpleInfoByOrderNo(String orderNo) {
        return orderMapper.queryOrderSimpleInfoByOrderNo(orderNo);
    }

    @Override
    public Map<String,String> queryOrderByOrderNo(String orderNo) {
        return orderMapper.queryOrderByOrderNo(orderNo);
    }
    @Override
    public Map<String,String> queryOrderDetailInfo(String orderNo){
        //先通过订单编号查找订单表里的订单信息
        Map<String,String> orderInfo = queryOrderByOrderNo(orderNo);
        //然后再获取其他信息
        orderInfo.put("goodsName",goodsMapper.queryGoodsNameByGoodsNo(
                orderInfo.get("goodsNo")));
        orderInfo.put("deliveryAddress",addressMapper.queryAddressByAddressNo(
                orderInfo.get("deliveryNo")));
        orderInfo.remove("deliveryNo");
        return orderInfo;
    }
}
