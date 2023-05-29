package com.project.campustaobao.controler;
import com.project.campustaobao.server.UserServer;
import com.project.campustaobao.utils.RegisterUser;
import com.project.campustaobao.utils.Request;
import com.project.campustaobao.utils.Session;
import com.project.campustaobao.vo.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


@RestController
public class LoginRegisterController {
    @Autowired
    private UserServer userServer;
    @GetMapping("/c2")
    public String test1(){
        HttpServletRequest request = (HttpServletRequest) Request.getRequest();
        System.out.println(request.getParameter("name"));
        System.out.println(request.getParameter("account"));
        return "我是第2个controller";
    }
    @GetMapping("/checkLogin")
    public String test(){
        return "已连接";
    }
    @PostMapping("/checkLogin")
    @ResponseBody
    /**
     * 我没有选用@RequestBody Map的形式，因为Map自动拆箱和装箱，性能不好
     */
    public boolean login(){
        HttpServletRequest req = (HttpServletRequest) Request.getRequest();
        String account = req.getParameter("account");
        String password = req.getParameter("password");
        Session.setSessionAttribute(req, "account",account);
        Session.setSessionAttribute(req,"password",password);
        Session.setSessionAttribute(req,"notLogin",false);
        return userServer.login(account,password);
    }
    @PostMapping("/register")
    @ResponseBody
    /**
     * 我没有选用@RequestBody Map的形式，因为Map自动拆箱和装箱，性能不好
     */
    public ResultMessage<String> register(){
        HttpServletRequest req = (HttpServletRequest)Request.getRequest();
        String tel= req.getParameter("tel");
        String password= req.getParameter("password");
        String userName= req.getParameter("username");
        if(!userServer.register(password,userName,tel)){
            return new ResultMessage<>(ResultMessage.ERROR_CODE,"同一个手机号不能多次注册","false");
        }
        return new ResultMessage<>(ResultMessage.SUCCESS_CODE,"恭喜你注册成功",RegisterUser.generateAccount(tel));
    }
}
