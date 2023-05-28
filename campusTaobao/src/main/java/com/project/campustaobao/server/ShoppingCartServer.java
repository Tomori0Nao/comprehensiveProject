package com.project.campustaobao.server;

import com.project.campustaobao.pojo.Goods;
import com.project.campustaobao.pojo.ShoppingCartGoods;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ShoppingCartServer {
    List<ShoppingCartGoods> queryAllShoppingCartGoods(String account);
    Goods queryShoppingCartGoodsByGoodsNo(String goodsNo);
}
