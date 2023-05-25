package com.project.campustaobao.controler;

import com.project.campustaobao.server.GoodsServer;
import com.project.campustaobao.vo.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 商城主页 商品展示
 */
@RestController
public class HomeController {
    @Autowired
    private GoodsServer goodsServer;
    @GetMapping("/images")
    public ResultMessage<List<String>> getImages(){
        ResultMessage<List<String>> resultMsg = new ResultMessage<>(ResultMessage.SUCCESS_CODE,"ok",
                goodsServer.queryGoodsImages("homeGoods-"));
        //System.out.println("img");
        return resultMsg;
    }
}
