package com.project.campustaobao.mapper;

import com.project.campustaobao.pojo.ShoppingCartGoods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface ShoppingCartMapper {
    /**
     * 获取用户购物车信息
     * @param account 用户账号
     * @return 该用户购物车全部商品
     */
    List<Map<String,Object>> queryAllShoppingCartGoods(@Param("account") String account);
    Map<String,Object> queryShoppingCartGoodsByGoodsNo(@Param("goodsNo") String goodsNo);
}
