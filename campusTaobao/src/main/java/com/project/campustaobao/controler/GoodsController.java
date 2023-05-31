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
}
