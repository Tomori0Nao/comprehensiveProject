package com.project.campustaobao.controler;

import com.project.campustaobao.pojo.Administrator;
import com.project.campustaobao.pojo.UserOrder;
import com.project.campustaobao.server.AdminServer;
import com.project.campustaobao.server.OrderServer;
import com.project.campustaobao.server.UserServer;
import com.project.campustaobao.utils.Request;
import com.project.campustaobao.vo.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AdminController {
    @Autowired
    private AdminServer adminServer;
    @Autowired
    private UserServer userServer;
    @Autowired
    private OrderServer orderServer;
    @GetMapping("/searchAdmin")
    @ResponseBody
    public ResultMessage<Administrator> getAdmin(String adminAccount){
        Administrator admin = adminServer.queryAdminByAccount(adminAccount);
        ResultMessage<Administrator> resultMessage;
        if(admin == null){
            resultMessage = new ResultMessage<>(ResultMessage.ERROR_CODE,
                    "此管理员不存在",null);
        }else{
            resultMessage = new ResultMessage<>(ResultMessage.SUCCESS_CODE,
                    "查找到此管理员",admin);
        }
        return resultMessage;
    }
    /**
     * 获取所有的普通管理员
     * @return 返回管理员集合
     */
    @GetMapping("/getAdmins")
    @ResponseBody
    public ResultMessage<List<Administrator>> getAdmins(){
        ResultMessage<List<Administrator>> resultMessage;
        List<Administrator> list = adminServer.queryAdminList();
        if(list == null){
            resultMessage = new ResultMessage<>(ResultMessage.ERROR_CODE,
                    "未获取所有普通管理员信息",null);
        }else{
            resultMessage = new ResultMessage<>(ResultMessage.SUCCESS_CODE,
                    "已获取所有普通管理员信息",list);
        }
        return resultMessage;
    }
    /**
     * 获取普通管理员部分信息
     * @return 返回管理员部分信息
     */
    @GetMapping("/getAdminSimpleInfo")
    @ResponseBody
    public ResultMessage<Map<String,String>> getAdminSimpleInfo(String adminAccount){
        //System.out.println(adminAccount);
        ResultMessage<Map<String,String>> resultMessage;
        Map<String,String> map = adminServer.queryAdminSimpleInfoByAdminAccount(adminAccount);
        System.out.println(map.get("adminAccount"));
        if(map == null){
            resultMessage = new ResultMessage<>(ResultMessage.ERROR_CODE,
                    "未获取该普通管理员的信息",null);
        }else{
            resultMessage = new ResultMessage<>(ResultMessage.SUCCESS_CODE,
                    "已获取该普通管理员信息",map);
        }
        return resultMessage;
    }
    /**
     * 更新普通管理员信息
     * @return 返回提示信息
     */
    @PostMapping("/updateAdminInfo")
    @ResponseBody
    public ResultMessage<String> updateAdminInfo(){
        //System.out.println(adminAccount);
        HttpServletRequest req = (HttpServletRequest) Request.getRequest();
        Map<String,String> adminSimpleInfo = new HashMap<>(4);
        adminSimpleInfo.put("adminAccount",req.getParameter("adminAccount"));
        adminSimpleInfo.put("adminPassword",req.getParameter("adminPassword"));
        adminSimpleInfo.put("adminNakeName",req.getParameter("adminNakeName"));
        adminSimpleInfo.put("adminType",req.getParameter("adminType"));
        //System.out.println(adminSimpleInfo);
        ResultMessage<String> resultMessage;
        boolean update = adminServer.updateAdminSimpleInfo(adminSimpleInfo);
        System.out.println(update);
        if(update){
            resultMessage = new ResultMessage<>(ResultMessage.SUCCESS_CODE,
                    "已经修改此管理员信息",null);
        }else{
            resultMessage = new ResultMessage<>(ResultMessage.ERROR_CODE,
                    "管理员信息修改失败",null);
        }
        return resultMessage;
    }
    @PostMapping("/addAdmin")
    public ResultMessage<Administrator> addAdmin(Administrator admin){
        //System.out.println(adminAccount);
        System.out.println("controller中"+admin);
        ResultMessage<Administrator> resultMessage;
        admin = adminServer.insertAdmin(admin);
        resultMessage = new ResultMessage<>(ResultMessage.SUCCESS_CODE,
                    "新增管理员成功",admin);
        return resultMessage;
    }
    @GetMapping("/searchUser")
    @ResponseBody
    public ResultMessage<Map<String,String>> searchUser(String userAccount){
        Map<String,String> u = userServer.queryUserSimpleInfoByAccount(userAccount);
        ResultMessage<Map<String,String>> resultMessage;
        if(u == null){
            resultMessage = new ResultMessage<>(ResultMessage.ERROR_CODE,
                    "没有找到该用户信息",null);
        }else{
            resultMessage = new ResultMessage<>(ResultMessage.SUCCESS_CODE,
                    "查找到该用户信息",u);
        }
        return resultMessage;
    }
    @GetMapping("/forbidAdmin")
    @ResponseBody
    //前端需要传递禁用的账号集合
    public ResultMessage<String> forbidAdmin(){
        HttpServletRequest req = (HttpServletRequest) Request.getRequest();
        String[] adminNoList = req.getParameterValues("adminNoList[]");
        for(String adminAccount:adminNoList){
            adminServer.forbidAdmin(adminAccount);
        }
        return new ResultMessage<>(ResultMessage.SUCCESS_CODE,
                "已经成功禁用该管理员",null);
    }
    @GetMapping("/notForbidAdmin")
    @ResponseBody
    //前端需要传递禁用的账号集合
    public ResultMessage<String> notForbidAdmin(){
        HttpServletRequest req = (HttpServletRequest) Request.getRequest();
        String[] adminNoList = req.getParameterValues("adminNoList[]");
        for(String adminAccount:adminNoList){
            adminServer.notForbidAdmin(adminAccount);
        }
        return new ResultMessage<>(ResultMessage.SUCCESS_CODE,
                "已经成功解除禁用该管理员",null);
    }
    @GetMapping("/forbidUser")
    @ResponseBody
    //前端需要传递禁用的账号集合
    public ResultMessage<String> forbidUser(){
        HttpServletRequest req = (HttpServletRequest) Request.getRequest();
        String[] userAccountList = req.getParameterValues("userAccountList[]");
        for(String userAccount:userAccountList){
            userServer.forbidUser(userAccount);
        }
        return new ResultMessage<>(ResultMessage.SUCCESS_CODE,
                "已经成功禁用该用户",null);
    }
    @GetMapping("/notForbidUser")
    @ResponseBody
    //前端需要传递禁用的账号集合
    public ResultMessage<String> notForbidUser(){
        HttpServletRequest req = (HttpServletRequest) Request.getRequest();
        String[] userAccountList = req.getParameterValues("userAccountList[]");
        for(String userAccount:userAccountList){
            userServer.notForbidUser(userAccount);
        }
        return new ResultMessage<>(ResultMessage.SUCCESS_CODE,
                "已经成功解除禁用该用户",null);
    }
    @GetMapping("/getOrders")
    @ResponseBody
    public ResultMessage<List<Map<String,String>>> getOrders(){
        List<Map<String,String>> ordersList = orderServer.queryAllOrders();
        ResultMessage<List<Map<String,String>>> resultMessage;
        if(ordersList == null){
            resultMessage = new ResultMessage<>(ResultMessage.ERROR_CODE,
                    "没有找到任何订单信息",null);
        }else{
            resultMessage = new ResultMessage<>(ResultMessage.SUCCESS_CODE,
                    "查找到所有订单信息",ordersList);
        }
        return resultMessage;
    }
    @GetMapping("/getOrderDetailInfo")
    @ResponseBody
    public ResultMessage<Map<String,String>> getOrderDetailInfo(String orderNo){
        Map<String,String> orderDetail = orderServer.queryOrderDetailInfo(orderNo);
        ResultMessage<Map<String,String>> resultMessage;
            resultMessage = new ResultMessage<>(ResultMessage.SUCCESS_CODE,
                    "查找该订单的详细信息",orderDetail);
        return resultMessage;
    }
    @GetMapping("/searchOrder")
    @ResponseBody
    public ResultMessage<Map<String,String>> searchOrder(String orderNo){
        Map<String,String> orderSimpleInfo = orderServer.querySimpleInfoByOrderNo(orderNo);
        ResultMessage<Map<String,String>> resultMessage;
        resultMessage = new ResultMessage<>(ResultMessage.SUCCESS_CODE,
                "查找该订单的详细信息",orderSimpleInfo);
        return resultMessage;
    }

}
