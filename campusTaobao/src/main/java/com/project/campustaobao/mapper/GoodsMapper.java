package com.project.campustaobao.mapper;

import com.project.campustaobao.pojo.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface GoodsMapper {
    String queryGoodsImage(String prefix);
    List<String> queryHomeGoods(String prefix);
    Goods queryShoppingCartGoodsByGoodsNo(@Param("goodsNo") String goodsNo);
}
