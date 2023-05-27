package com.project.campustaobao.server;

import java.util.List;
import java.util.Map;

public interface GoodsServer {
    List<Map<String, String>> queryGoodsImages(String prefix);
    Map<String,String> queryGoodsInfoByGoodsNo(String goodsNo);
}
