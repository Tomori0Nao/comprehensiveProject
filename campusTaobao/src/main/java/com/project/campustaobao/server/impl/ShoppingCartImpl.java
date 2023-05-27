package com.project.campustaobao.server.impl;

import com.project.campustaobao.mapper.GoodsMapper;
import com.project.campustaobao.mapper.ShoppingCartMapper;
import com.project.campustaobao.pojo.Goods;
import com.project.campustaobao.pojo.ShoppingCartGoods;
import com.project.campustaobao.server.ShoppingCartServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ShoppingCartImpl implements ShoppingCartServer {
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private ShoppingCartMapper cartMapper;
    @Override
    public List<ShoppingCartGoods> queryAllShoppingCartGoods(String account) {
        List<Map<String,Object>> cartGoodsList = cartMapper.queryAllShoppingCartGoods(account);
        List<ShoppingCartGoods> goodsList = new ArrayList<>(cartGoodsList.size());
        //在map中取出来商品编号
        for(Map<String,Object> cartGoods : cartGoodsList){
            String goodsNo = (String)cartGoods.get("goods_no");
            //拿着取出来的编号查找goods_info中的实际信息，这样是为了获得商品的具体信息
            //为什么没用多对一处理?
            // 因为shoppingCart中没有Goods类型的属性
            Goods goods = goodsMapper.queryShoppingCartGoodsByGoodsNo(goodsNo);
            String totalCost = (String)cartGoods.get("total_cost");
            int goodsNumber =
                    Integer.parseInt((String)cartGoods.get("goods_num"));
            ShoppingCartGoods shoppingCartGoods = new ShoppingCartGoods(goods.getGoodsName(),goods.getGoodsImageName(),
                    goods.getStoreName(),goods.getGoodsPrice(),totalCost,goodsNumber, goods.getVipDerate(),goods.getMaxNumOfSinglePurchase());
            goodsList.add(shoppingCartGoods);
        }
        return goodsList;
    }
}
