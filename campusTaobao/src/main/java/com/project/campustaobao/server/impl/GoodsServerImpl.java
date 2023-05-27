package com.project.campustaobao.server.impl;

import com.project.campustaobao.mapper.GoodsMapper;
import com.project.campustaobao.server.GoodsServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GoodsServerImpl implements GoodsServer {
    @Autowired
    private GoodsMapper goodsMapper;
    @Override
    public List<Map<String, String>> queryGoodsImages(String prefix) {
        return goodsMapper.queryHomeGoods(prefix);
    }

    @Override
    public Map<String, String> queryGoodsInfoByGoodsNo(String goodsNo) {
        return goodsMapper.queryGoodsInfoByGoodsNo(goodsNo);
    }
}
