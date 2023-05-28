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
    private int begin = 0;
    private static final int count = 5;
    @GetMapping("/homeGoods")
    public ResultMessage<List<Map<String, String>>> getHomeGoods(){
        ResultMessage<List<Map<String,String>>> resultMsg = new ResultMessage<>(ResultMessage.SUCCESS_CODE,"ok",
                goodsServer.queryHomeGoods(begin,count));
        //System.out.println("img");
        System.out.println("主页请求图片");
        begin += count;
        if(resultMsg.getData().size() == 0){
            begin = 0;
        }
        return resultMsg;
    }
}
