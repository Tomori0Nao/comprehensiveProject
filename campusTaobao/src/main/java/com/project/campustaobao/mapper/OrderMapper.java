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
    List<Map<String,Object>> queryOrderListByAccount(@Param("account") String account);
    boolean deleteOrderByOrderNo(@Param("orderNo") String orderNo);
}
