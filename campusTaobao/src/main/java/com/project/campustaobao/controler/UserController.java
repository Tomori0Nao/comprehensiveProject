package com.project.campustaobao.controler;

import com.project.campustaobao.pojo.ShoppingCart;
import com.project.campustaobao.pojo.ShoppingCartGoods;
import com.project.campustaobao.pojo.User;
import com.project.campustaobao.pojo.VIPUser;
import com.project.campustaobao.server.UserServer;
import com.project.campustaobao.vo.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import java.util.List;

@CrossOrigin
@RestController
public class UserController {
    //    @Resource
    @Autowired
    private UserServer userServer;
    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    @ResponseBody
    public String findAllUser1(){
        return "helloworld";
    }
    @RequestMapping(value = "/image",method = RequestMethod.GET)
    @ResponseBody
    public String getImage(String img){
        System.out.println(img);
        return "image";
    }
    @GetMapping("/vipInfo")
    @ResponseBody
    public ResultMessage<VIPUser> getUserInfo(String vipAccount){
        ResultMessage<VIPUser> resultMessage = new ResultMessage<>(
                ResultMessage.SUCCESS_CODE,"vip信息请求成功",
                userServer.queryVIPUserByAccount(vipAccount)
        );
        return resultMessage;
    }
    @GetMapping("/shoppingCart")
    @ResponseBody
    public ResultMessage<List<ShoppingCartGoods>> getUserShoppingCart(String account){
        ResultMessage<List<ShoppingCartGoods>> resultMessage = new ResultMessage<>(
                ResultMessage.SUCCESS_CODE,"vip信息请求成功",
                userServer.queryAllShoppingCartGoods(account)
        );
        return resultMessage;
    }


}