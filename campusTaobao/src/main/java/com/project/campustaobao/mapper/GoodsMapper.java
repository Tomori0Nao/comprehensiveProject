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
    List<Goods> queryAllGoods();
    Goods  queryGoodsByGoodsNo(@Param("goodsNo") String goodsNo);
    String queryGoodsNameByGoodsNo(@Param("goodsNo")String goodsNo);
    /**
     * 通过商品编号查找相似商品
     * @param goodsNo 商品编号
     * @return 相似商品的集合
     */
    List<Goods> querySimilarGoodsByGoodsNo(String goodsNo);

    /**
     * 通过商品编号查找商品种类
     * @param goodsNo 商品编号
     * @return 商品种类
     */
    String queryGoodsTypeByGoodsNo(String goodsNo);

    /**
     * 通过商品种类返回相似商品
     * 商品信息只要部分信息： 编号、名称、价格、商品图片
     * @param goodsType 商品种类
     * @return 商品种类相同的商品集合
     */
    List<Map<String,String>> querySimilarGoodsByGoodsType(@Param("goodsType") String goodsType);
    int queryCategoryCount();
    boolean insertGoods(@Param("goods")Goods goods);
    boolean deleteGoodsByGoodsNo(@Param("goodsNo")String goodsNo);
    boolean deleteGoodsByGoodsType(@Param("goodType")String goodsType);
    String queryCategoryNameByCategoryNo(@Param("categoryNo")String categoryNo);
    boolean updateGoodsInfo(@Param("goods")Goods goods);
    boolean insertGoodsType(@Param("categoryNo")String categoryNo, @Param("categoryName")String goodsType);
    String goodsTypeIsExist(@Param("categoryName")String categoryName);
    boolean updateGoodsType(@Param("categoryNo")String categoryNo, @Param("categoryName")String goodsType);
    boolean updateGoodsTypeByGoodsType(@Param("goodsOldType")String oldType,@Param("goodsNewType")String newType);
    boolean deleteGoodsType(@Param("categoryName")String goodsType);
}
