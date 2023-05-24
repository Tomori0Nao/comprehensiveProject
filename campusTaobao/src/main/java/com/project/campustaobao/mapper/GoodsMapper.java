package com.project.campustaobao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface GoodsMapper {
    String queryGoodsImage(String prefix);
    List<String> queryHomeGoods(String prefix);
}
