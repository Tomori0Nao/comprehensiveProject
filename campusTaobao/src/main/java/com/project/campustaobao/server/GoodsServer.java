package com.project.campustaobao.server;

import com.project.campustaobao.pojo.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface GoodsServer {
    boolean addGoods(Goods goods);
    /**
     * 查找商品的详细信息
     * 用于商品详情页
     * @param goodsNo 商品编号
     * @return
     */
    Map<String,String> queryGoodsInfoByGoodsNo(@Param("goodsNo") String goodsNo);

    /**
     * 查询首页商品
     * @param begin 已经查询到的序号
     * @param count 数量，默认为5
     * @return 集合里面是多个商品的部分信息，用map存放
     */
    List<Map<String,String>> queryHomeGoods(@Param("begin") int begin,
                                            @Param("count") int count);
    Goods queryGoodsByGoodsNo(String goodsNo);

    List<Goods> queryAllGoods();
    /**
     * 通过商品编号查找相似商品
     * @param goodsNo 商品编号
     * @return 相似商品的集合
     */
    List<Map<String,String>> querySimilarGoodsByGoodsNo(String goodsNo);

    /**
     * 通过商品编号查找商品种类
     * @param goodsNo 商品编号
     * @return 商品种类
     */
    String queryGoodsTypeByGoodsNo(String goodsNo);

    /**
     * 通过商品种类返回相似商品 (只是部分信息，所以用Map存储)
     * @param goodsType 商品种类
     * @return 商品种类相同的商品集合
     */
    List<Map<String,String>> querySimilarGoodsByGoodsType(String goodsType);

    /**
     * 通过商品种类删除商品
     * @param goodsType 商品种类
     * @return 删除成功返回true
     */
    boolean deleteGoodsByGoodsType(String goodsType);

    /**
     * 通过商品编号删除商品
     * @param goodsNo 商品编号
     * @return 删除成功返回true
     */
    boolean deleteGoodsByGoodsNo(String goodsNo);

    boolean updateGoodsInfo(Goods goods);
    boolean addGoodsType(String goodsType);
    boolean goodsTypeIsExist(String categoryNo);
    boolean updateGoodsType(String categoryNo,String goodsType);
    boolean updateGoodsTypeByGoodsType(String oldType,String newType);
    boolean deleteGoodsType(String categoryNo);
    double queryGoodsPriceByGoodsNo(String goodsNo);
}
