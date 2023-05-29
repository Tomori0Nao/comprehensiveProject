package com.project.campustaobao.controler;

import com.project.campustaobao.pojo.*;
import com.project.campustaobao.server.DeliveryAddressServer;
import com.project.campustaobao.server.UserServer;
import com.project.campustaobao.utils.Request;
import com.project.campustaobao.utils.Session;
import com.project.campustaobao.vo.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class UserController {
    //    @Resource
    @Autowired
    private UserServer userServer;
    @Autowired
    private DeliveryAddressServer addressServer;

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
    public ResultMessage<VIPUser> getVIPUserInfo(String vipAccount){
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
    @GetMapping("/userInfo")
    @ResponseBody
    public ResultMessage<Map<String,String>> getUserInfo(String account){
        Map<String,String> useInfoMap = userServer.queryUserInfoByAccount(account);
        if(useInfoMap != null){
            ResultMessage<Map<String,String>> resultMessage = new ResultMessage<>(
                    ResultMessage.SUCCESS_CODE,"查找到用户基本信息",useInfoMap);
            return resultMessage;
        }
        return null;
    }

    /**
     * 用户请求新增收货地址
     * @param name 收货人姓名
     * @param tel  收货人电话
     * @param address 收货地址
     * @return 返回ResultMessage的data是DeliveryAddress的对象
     */
    @GetMapping("/addAddress")
    @ResponseBody

    public ResultMessage<DeliveryAddress> addDeliveryAddress(String name,String tel,String address,String account){
//       应该在session中取出来账号，后期记得修改
//        String account = (String)Session.getSessionAttribute(Request.getRequest(),"account");
        //System.out.println("account =" +account);
        DeliveryAddress addr = addressServer.insertDeliveryAddress(account,
                name,tel,address);
            ResultMessage<DeliveryAddress> resultMessage = new ResultMessage<>(
                    ResultMessage.SUCCESS_CODE,"ok",addr);
            return resultMessage;

    }
    @GetMapping("/deliveryAddress")
    @ResponseBody
    public ResultMessage<List<DeliveryAddress>> getAllDeliveryAddress(String account){
        List<DeliveryAddress> list = addressServer.getDeliveryAddressList(account);
        ResultMessage<List<DeliveryAddress>> resultMessage = new ResultMessage<>(
                ResultMessage.SUCCESS_CODE,"ok",list);
        return resultMessage;

    }


}
