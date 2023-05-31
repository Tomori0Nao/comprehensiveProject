package com.project.campustaobao.controler;


import com.project.campustaobao.pojo.Goods;
import com.project.campustaobao.server.GoodsServer;
import com.project.campustaobao.utils.Request;
import com.project.campustaobao.vo.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
public class GoodsController {
    @Autowired
    private GoodsServer goodsServer;

    @GetMapping("/goodsInfo")
    @ResponseBody
    public ResultMessage<Map<String,String>> getGoodsInfo(String goodsNo){
        if(goodsNo == null){
            return null;
        }
        Map<String,String> goodsInfoMap = goodsServer.queryGoodsInfoByGoodsNo(goodsNo);
        if(goodsInfoMap == null){
            return new ResultMessage<>(ResultMessage.ERROR_CODE,goodsNo+"商品信息请求失败",null);
        }else{
            return new ResultMessage<>(ResultMessage.SUCCESS_CODE,goodsNo+"商品信息请求成功",goodsInfoMap);
        }
    }
    @GetMapping("/similarGoods")
    @ResponseBody
    public ResultMessage<List<Map<String,String>>> getSimilarGoods(String goodsNo){
        ResultMessage<List<Map<String,String>>> resultMsg = new ResultMessage<>(ResultMessage.SUCCESS_CODE,"ok",
                goodsServer.querySimilarGoodsByGoodsNo(goodsNo));
        String type = goodsServer.queryGoodsTypeByGoodsNo(goodsNo);
        List list = goodsServer.querySimilarGoodsByGoodsType(type);
        return resultMsg;
    }
    @GetMapping("/addGoods")
    @ResponseBody
    public ResultMessage<String> addGoods(Goods goods){
        System.out.println(goods);
        String date = goods.getGoodsPurchaseDate();
        int t = date.indexOf(' ');
        goods.setGoodsPurchaseDate(date.substring(0,t));
        ResultMessage<String> resultMsg;
        boolean add = goodsServer.addGoods(goods);
        if(add) {
            resultMsg = new ResultMessage<>(ResultMessage.SUCCESS_CODE,
                    "商品添加成功", null);
        }else{
            resultMsg = new ResultMessage<>(ResultMessage.ERROR_CODE,
                    "商品添加失败", null);
        }
        return resultMsg;
    }
    @GetMapping("/deleteGoods")
    @ResponseBody
    public ResultMessage<String> deleteGoods(String goodsNo){
        System.out.println(goodsNo);
        ResultMessage<String> resultMsg;
        boolean add = goodsServer.deleteGoodsByGoodsNo(goodsNo);
        if(add) {
            resultMsg = new ResultMessage<>(ResultMessage.SUCCESS_CODE,
                    "商品删除成功", null);
        }else{
            resultMsg = new ResultMessage<>(ResultMessage.ERROR_CODE,
                    "商品删除失败", null);
        }
        return resultMsg;
    }
    @GetMapping("/updateGoodsInfo")
    @ResponseBody
    public ResultMessage<String> updateGoodsInfo(Goods goods){
        System.out.println(goods);
        ResultMessage<String> resultMsg;
        boolean add = goodsServer.updateGoodsInfo(goods);
        if(add) {
            resultMsg = new ResultMessage<>(ResultMessage.SUCCESS_CODE,
                    "商品修改成功", null);
        }else{
            resultMsg = new ResultMessage<>(ResultMessage.ERROR_CODE,
                    "商品修改失败", null);
        }
        return resultMsg;
    }
    @GetMapping("/addGoodsType")
    @ResponseBody
    public ResultMessage<String> addGoodsType(String categoryName){
        System.out.println(categoryName);
        ResultMessage<String> resultMsg;
        boolean add = goodsServer.addGoodsType(categoryName);
        if(add) {
            resultMsg = new ResultMessage<>(ResultMessage.SUCCESS_CODE,
                    "商品种类添加成功", null);
        }else{
            resultMsg = new ResultMessage<>(ResultMessage.ERROR_CODE,
                    "商品种类添加失败", null);
        }
        return resultMsg;
    }

    @GetMapping("/updateGoodsType")
    @ResponseBody
    public ResultMessage<String> updateGoodsType(String categoryNo,String categoryName){
        System.out.println(categoryNo);
        ResultMessage<String> resultMsg;
        boolean add = goodsServer.updateGoodsType(categoryNo, categoryName);
        if(add) {
            resultMsg = new ResultMessage<>(ResultMessage.SUCCESS_CODE,
                    "商品种类修改成功", null);
        }else{
            resultMsg = new ResultMessage<>(ResultMessage.ERROR_CODE,
                    "商品种类修改失败", null);
        }
        return resultMsg;
    }
    @GetMapping("/deleteGoodsType")
    @ResponseBody
    public ResultMessage<String> deleteGoodsType(String categoryNo){
        System.out.println(categoryNo);
        ResultMessage<String> resultMsg;
        boolean add = goodsServer.deleteGoodsType(categoryNo);
        if(add) {
            resultMsg = new ResultMessage<>(ResultMessage.SUCCESS_CODE,
                    "商品种类删除成功", null);
        }else{
            resultMsg = new ResultMessage<>(ResultMessage.ERROR_CODE,
                    "商品种类删除失败", null);
        }
        return resultMsg;
    }
}
