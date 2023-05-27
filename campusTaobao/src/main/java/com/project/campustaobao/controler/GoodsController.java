package com.project.campustaobao.controler;


import com.project.campustaobao.server.GoodsServer;
import com.project.campustaobao.vo.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
}
