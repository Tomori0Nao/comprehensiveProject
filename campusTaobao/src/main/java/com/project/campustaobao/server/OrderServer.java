package com.project.campustaobao.server;
import com.project.campustaobao.pojo.UserOrder;
import java.util.List;
import java.util.Map;


public interface OrderServer {
    /**
     * 管理员查看订单
     * @return
     */
    List<Map<String,String>> queryAllOrders();
    List<UserOrder> getOrderListByAccount(String account);

    boolean deleteOrder(String orderNo);
    Map<String,String> querySimpleInfoByOrderNo(String orderNo);

    Map<String,String> queryOrderByOrderNo(String orderNo);

    Map<String, String> queryOrderDetailInfo(String orderNo);

    boolean pay(String account, String orderNo, Integer goodsNumber, String deliveryAddressNo);
}
