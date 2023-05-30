package com.project.campustaobao.server;
import com.project.campustaobao.pojo.UserOrder;
import java.util.List;


public interface OrderServer {
    List<UserOrder> getOrderListByAccount(String account);

    boolean deleteOrder(String orderNo);
}
