package com.project.campustaobao.controler;

import com.project.campustaobao.server.GoodsServer;
import com.project.campustaobao.vo.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 商城主页 商品展示
 */
@RestController
public class HomeController {
    @Autowired
    private GoodsServer goodsServer;
    @GetMapping("/homeImages")
    public ResultMessage<List<Map<String, String>>> getImages(){
        ResultMessage<List<Map<String,String>>> resultMsg = new ResultMessage<>(ResultMessage.SUCCESS_CODE,"ok",
                goodsServer.queryGoodsImages("homeGoods-"));
        //System.out.println("img");
        System.out.println();
        return resultMsg;
    }
}
