package com.project.campustaobao.server;

import com.project.campustaobao.pojo.ShoppingCartGoods;

import java.util.List;

public interface ShoppingCartServer {
    List<ShoppingCartGoods> queryAllShoppingCartGoods(String account);
}
