package com.project.campustaobao.server.impl;

import com.project.campustaobao.mapper.GoodsMapper;
import com.project.campustaobao.pojo.Goods;
import com.project.campustaobao.server.GoodsServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class GoodsServerImpl implements GoodsServer {
    @Autowired
    private GoodsMapper goodsMapper;
    public List<Map<String, String>> queryHomeGoods(int begin,int count) {
        return goodsMapper.queryHomeGoods(begin,count);
    }

    @Override
    public Goods queryGoodsByGoodsNo(String goodsNo) {
        return goodsMapper.queryGoodsByGoodsNo(goodsNo);
    }

    @Override
    public List<Goods> queryAllGoods() {
        return goodsMapper.queryAllGoods();
    }

    @Override
    public List<Map<String,String>> querySimilarGoodsByGoodsNo(String goodsNo) {
        /*
          1.首先根据商品编号查找到商品的种类
          2.根据商品种类查找该种类的商品
         */
        String goodsType = goodsMapper.queryGoodsTypeByGoodsNo(goodsNo);
        return goodsMapper.querySimilarGoodsByGoodsType(goodsType);
    }

    /**
     * 查找某商品的商品种类
     * @param goodsNo 商品编号
     * @return 该编号的商品的商品种类
     */
    @Override
    public String queryGoodsTypeByGoodsNo(String goodsNo) {
        return goodsMapper.queryGoodsTypeByGoodsNo(goodsNo);
    }

    /**
     * 查找某个种类的所有商品信息(只是部分信息，所以用Map存储)
     * @param goodsType 商品种类
     * @return 返回该商品种类对应下的商品集合
     */
    @Override
    public List<Map<String,String>> querySimilarGoodsByGoodsType(String goodsType) {
        return goodsMapper.querySimilarGoodsByGoodsType(goodsType);
    }

    /**
     * 通过商品种类删除商品
     *
     * @param goodsType 商品种类
     * @return 删除成功返回true
     */
    @Override
    public boolean deleteGoodsByGoodsType(String goodsType) {
        return goodsMapper.deleteGoodsByGoodsType(goodsType);
    }

    /**
     * 通过商品编号删除商品
     *
     * @param goodsNo 商品编号
     * @return 删除成功返回true
     */
    @Override
    public boolean deleteGoodsByGoodsNo(String goodsNo) {
        return goodsMapper.deleteGoodsByGoodsNo(goodsNo);
    }

    @Override
    public boolean updateGoodsInfo(Goods goods) {
        return goodsMapper.updateGoodsInfo(goods);
    }

    @Override
    public boolean addGoodsType(String goodsType) {
        if(goodsTypeIsExist(goodsType)){
            return false;
        }
        int count = goodsMapper.queryCategoryCount();
        String categoryNo = "category" + count + goodsType.hashCode();
        return goodsMapper.insertGoodsType(categoryNo, goodsType);
    }

    @Override
    public boolean goodsTypeIsExist(String categoryName) {
        return goodsMapper.goodsTypeIsExist(categoryName) != null;
    }

    @Override
    public boolean updateGoodsType(String categoryNo, String goodsType) {
        //先获取原本的商品种类
        String type = goodsMapper.queryCategoryNameByCategoryNo(categoryNo);
        //将此种类修改
        if(goodsMapper.updateGoodsType(categoryNo, goodsType)){
            //然后将商品表中此类商品修改种类
            updateGoodsTypeByGoodsType(type,goodsType);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateGoodsTypeByGoodsType(String oldType, String newType) {
        return goodsMapper.updateGoodsTypeByGoodsType(oldType, newType);
    }

    @Override
    public boolean deleteGoodsType(String categoryNo) {
        //先获取原本的商品种类
        String type = goodsMapper.queryCategoryNameByCategoryNo(categoryNo);
        //将此种类删除
        if(goodsMapper.deleteCategoryNameByCategoryNo(categoryNo)){
            //然后将商品表中此类商品删除
            deleteGoodsByGoodsType(type);
            return true;
        }
        return false;
    }

    @Override
    public boolean addGoods(Goods goods) {
        int count = goodsMapper.queryGoodsAccount();
        goods.setGoodsNo("goods"+count+goods.getGoodsName().hashCode());
        return goodsMapper.insertGoods(goods);
    }

    @Override
    public Map<String, String> queryGoodsInfoByGoodsNo(String goodsNo) {
        return goodsMapper.queryGoodsInfoByGoodsNo(goodsNo);
    }


}
