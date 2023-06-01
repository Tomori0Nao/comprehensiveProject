package com.project.campustaobao.mapper;

import com.project.campustaobao.pojo.UserOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface OrderMapper {
    /**
     * 管理员用来查看所有的订单
     * @return 所有的订单集合
     */
    List<Map<String,String>> queryAllOrders();
    List<Map<String,Object>> queryOrderListByAccount(@Param("account") String account);
    boolean deleteOrderByOrderNo(@Param("orderNo") String orderNo);
    Map<String,String> queryOrderByOrderNo(@Param("orderNo")String orderNo);
    Map<String,String> queryOrderSimpleInfoByOrderNo(@Param("orderNo")String orderNo);

    boolean insertOrderByAccount(Map<String,Object> map);
}
