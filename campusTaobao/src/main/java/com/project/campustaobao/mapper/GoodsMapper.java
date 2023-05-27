package com.project.campustaobao.mapper;

import com.project.campustaobao.pojo.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface GoodsMapper {
    Map<String,String> queryGoodsInfoByGoodsNo(@Param("goodsNo") String goodsNo);
    List<Map<String,String>> queryHomeGoods(String prefix);
    Goods queryShoppingCartGoodsByGoodsNo(@Param("goodsNo") String goodsNo);
}
