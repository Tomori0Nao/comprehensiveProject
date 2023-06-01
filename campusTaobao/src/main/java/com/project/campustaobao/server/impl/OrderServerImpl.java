package com.project.campustaobao.server.impl;

import com.project.campustaobao.mapper.DeliveryAddressMapper;
import com.project.campustaobao.mapper.GoodsMapper;
import com.project.campustaobao.mapper.OrderMapper;
import com.project.campustaobao.mapper.UserMapper;
import com.project.campustaobao.pojo.Goods;
import com.project.campustaobao.pojo.UserOrder;
import com.project.campustaobao.server.OrderServer;
import com.project.campustaobao.server.UserServer;
import com.project.campustaobao.utils.DateUtils;
import com.project.campustaobao.utils.Request;
import com.project.campustaobao.utils.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    private UserServer userServer;
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

    /**
     * 获取订单的部分信息
     * @param orderNo 订单编号
     * @return 订单的部分信息
     */
    @Override
    public Map<String, String> querySimpleInfoByOrderNo(String orderNo) {
        return orderMapper.queryOrderSimpleInfoByOrderNo(orderNo);
    }

    /**
     * 查找订单表里卖弄某订单的信息
     * @param orderNo 订单编号
     * @return 返回订单表里面的订单信息(所以不是UserOrder对象，而是一个Map)
     */
    @Override
    public Map<String,String> queryOrderByOrderNo(String orderNo) {
        return orderMapper.queryOrderByOrderNo(orderNo);
    }

    /**
     * 查询订单详细信息
     * @param orderNo 订单编号
     * @return 订单的详细信息
     */
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

    @Override
    public boolean pay(String account, String goodsNo, Integer goodsNumber, String deliveryAddressNo) {
        Map<String,Object> map = new HashMap<>();
        map.put("orderNo","order"+(goodsNo+goodsNumber).hashCode()+"");
        map.put("userAccount",account);
        //下单时间
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime time = LocalDateTime.now();
        String localDateTime = df.format(time);
        LocalDateTime ldt = LocalDateTime.parse(localDateTime, df);
        System.out.println(ldt);
        map.put("orderTime",ldt);
        map.put("deliveryNo",deliveryAddressNo);
        map.put("goodsNo",goodsNo);
        Goods goods = goodsMapper.queryGoodsByGoodsNo(goodsNo);
        String price = goods.getGoodsPrice();
        String derate = goods.getVipDerate();
        String actualPayment = Double.parseDouble(price) * goodsNumber +"";
        //计算实际价格
        if(userServer.isVIPUser(account)){
            double p = Double.parseDouble(price) - Double.parseDouble(derate);
            actualPayment = p * goodsNumber +"";
            price = p +"";
        }
        map.put("orderPrice",price);
        map.put("orderDerate",derate);
        map.put("actualPayment",actualPayment);
        map.put("goodsNumber",goodsNumber);
        if(orderMapper.insertOrderByAccount(map)){
            userServer.deleteCartGoods(account,goodsNo);
            return true;
        }
        return false;
    }
}
