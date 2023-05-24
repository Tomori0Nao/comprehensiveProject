package com.project.campustaobao.server.impl;

import com.project.campustaobao.mapper.GoodsMapper;
import com.project.campustaobao.server.GoodsServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GoodsServerImpl implements GoodsServer {
    @Autowired
    private GoodsMapper goodsImageMapper;
    @Override
    public List<String> queryGoodsImages(String prefix) {
        return goodsImageMapper.queryHomeGoods(prefix);
    }
}
