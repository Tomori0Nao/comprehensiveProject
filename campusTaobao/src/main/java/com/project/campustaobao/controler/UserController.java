package com.project.campustaobao.controler;

import com.project.campustaobao.pojo.*;
import com.project.campustaobao.server.DeliveryAddressServer;
import com.project.campustaobao.server.OrderServer;
import com.project.campustaobao.server.UserServer;
import com.project.campustaobao.utils.Request;
import com.project.campustaobao.vo.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    private OrderServer orderServer;
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public String findAllUser1() {
        return "helloworld";
    }

    @RequestMapping(value = "/image", method = RequestMethod.GET)
    @ResponseBody
    public String getImage(String img) {
        System.out.println(img);
        return "image";
    }

    @GetMapping("/getUserStatus")
    @ResponseBody
    public ResultMessage<Boolean> getUserStatus(String userAccount){
        return new ResultMessage<>(ResultMessage.SUCCESS_CODE,
                "查找到该用户的状态",userServer.isBaned(userAccount));
    }

    @GetMapping("/vipInfo")
    @ResponseBody
    public ResultMessage<VIPUser> getVIPInfo(String vipAccount) {
        ResultMessage<VIPUser> resultMessage = new ResultMessage<>(
                ResultMessage.SUCCESS_CODE, "vip信息请求成功",
                userServer.queryVIPUserByAccount(vipAccount)
        );
        return resultMessage;
    }
    @GetMapping("/shoppingCart")
    @ResponseBody
    public ResultMessage<List<ShoppingCartGoods>> getShoppingCart(String account) {
        ResultMessage<List<ShoppingCartGoods>> resultMessage = new ResultMessage<>(
                ResultMessage.SUCCESS_CODE, "vip信息请求成功",
                userServer.queryAllShoppingCartGoods(account)
        );
        return resultMessage;
    }
    @GetMapping("/getUsers")
    @ResponseBody
    public ResultMessage<List<Map<String,String>>> getUsers(){
        List<Map<String,String>> userList = userServer.queryUsers();
        ResultMessage<List<Map<String,String>>> resultMessage;
        if(userList == null){
            resultMessage = new ResultMessage<>(
                    ResultMessage.SUCCESS_CODE,"没有一个用户",null);
        }else{
            resultMessage = new ResultMessage<>(
                    ResultMessage.SUCCESS_CODE,"查找到所有用户",userList);
        }
        return resultMessage;
    }


    @GetMapping("/userInfo")
    @ResponseBody
    public ResultMessage<Map<String, String>> getInfo(String account) {
        Map<String, String> useInfoMap = userServer.queryUserInfoByAccount(account);
        if (useInfoMap != null) {
            ResultMessage<Map<String, String>> resultMessage = new ResultMessage<>(
                    ResultMessage.SUCCESS_CODE, "查找到用户基本信息", useInfoMap);
            return resultMessage;
        }
        return null;
    }

    /**
     * 添加一个收货地址
     * @param address 前端发送过来的收货地址信息
     * @return 返回成功添加后的收货地址
     */
    @GetMapping("/addAddress")
    @ResponseBody

    public ResultMessage<DeliveryAddress> addDeliveryAddress(DeliveryAddress address) {
//       应该在session中取出来账号，后期记得修改
//        String account = (String)Session.getSessionAttribute(Request.getRequest(),"account");
        //System.out.println("account =" +account);
        //System.out.println(address);
        DeliveryAddress addr = addressServer.insertDeliveryAddress(address.getUserAccount(),
                address.getConsigneeName(), address.getConsigneeTel(), address.getAddress());
        ResultMessage<DeliveryAddress> resultMessage = null;
        if (addr == null) {
            resultMessage = new ResultMessage<>(
                    ResultMessage.ERROR_CODE, "同一个收货地址不能多次添加", null);
        } else {
            resultMessage = new ResultMessage<>(
                    ResultMessage.SUCCESS_CODE, "收货地址添加成功", addr);
        }
        return resultMessage;
    }

    /**
     * 获取用户所有的收获地址
     * @param account 用户账号
     * @return 返回该账号的用户的所有的收货地址
     */
    @GetMapping("/deliveryAddress")
    @ResponseBody
    public ResultMessage<List<DeliveryAddress>> getAllDeliveryAddress(String account) {
        //       应该在session中取出来账号，后期记得修改
        List<DeliveryAddress> list = addressServer.getDeliveryAddressList(account);
        ResultMessage<List<DeliveryAddress>> resultMessage = new ResultMessage<>(
                ResultMessage.SUCCESS_CODE, "ok", list);
        return resultMessage;
    }

    @GetMapping("/deleteDeliveryAddress")
    @ResponseBody
    public ResultMessage<String> deleteDeliveryAddress(String account, String addressNo) {
        //       应该在session中取出来账号，后期记得修改
        System.out.println(account);
        System.out.println(addressNo);
        boolean delete = addressServer.deleteDeliveryAddress(account, addressNo);
        System.out.println(delete);
        ResultMessage<String> resultMessage = null;
        if (delete) {
            resultMessage = new ResultMessage<>(
                    ResultMessage.SUCCESS_CODE, "地址删除成功", null);
        } else {
            resultMessage = new ResultMessage<>(
                    ResultMessage.ERROR_CODE, "地址删除失败", null);
        }
        return resultMessage;
    }

    @PostMapping("/editDeliveryAddress")
    @ResponseBody
    public ResultMessage<String> editDeliveryAddress(DeliveryAddress address) {
        //       应该在session中取出来账号，后期记得修改
        //然后将账号给address即address.setUserAccount(account)
        System.out.println(address);
        boolean update = addressServer.updateDeliveryAddress(address);
        System.out.println(update);
        ResultMessage<String> resultMessage;
        if (update) {
            resultMessage = new ResultMessage<>(
                    ResultMessage.SUCCESS_CODE, "地址修改成功", null);
        } else {
            resultMessage = new ResultMessage<>(
                    ResultMessage.ERROR_CODE, "地址修改失败，可能已经存在这个地址", null);
        }
        return resultMessage;
    }

    @GetMapping("/setUserName")
    @ResponseBody
    public ResultMessage<String> setNakeName(String account, String newName) {
        //       应该在session中取出来账号，后期记得修改
        boolean update = userServer.updateUserNameByAccount(account, newName);
        //System.out.println(update);
        ResultMessage<String> resultMessage = null;
        if (update) {
            resultMessage = new ResultMessage<>(
                    ResultMessage.SUCCESS_CODE, "昵称修改成功", null);
        } else {
            resultMessage = new ResultMessage<>(
                    ResultMessage.ERROR_CODE, "昵称修改失败", null);
        }

        return resultMessage;
    }
    @GetMapping("/userOrder")
    @ResponseBody
    public ResultMessage<List<UserOrder>> getUserOrderList(String account){
        //这里的account实际上不用前端传递，可以直接在session中获得
        List<UserOrder> orderList = orderServer.getOrderListByAccount(account);
        ResultMessage<List<UserOrder>> resultMessage;
        if(orderList != null){
            resultMessage = new ResultMessage<>(ResultMessage.SUCCESS_CODE,
                    "查找到该用户所有订单信息",orderList);
        }
        else{
            resultMessage = new ResultMessage<>(ResultMessage.SUCCESS_CODE,
                    "该用户好没有任何订单",null);
        }
        return resultMessage;
    }
    @GetMapping("/deleteUserOrder")
    @ResponseBody
    public ResultMessage<String> deleteUserOrder(String account,String orderNo){
        //这里的account实际上不用前端传递，可以直接在session中获得
        boolean delete = orderServer.deleteOrder(orderNo);
        ResultMessage<String> resultMessage;
        if(delete){
            resultMessage = new ResultMessage<>(ResultMessage.SUCCESS_CODE,
                    "成功删除此订单",null);
        }
        else{
            resultMessage = new ResultMessage<>(ResultMessage.ERROR_CODE,
                    "删除订单失败",null);
        }
        return resultMessage;
    }

}
