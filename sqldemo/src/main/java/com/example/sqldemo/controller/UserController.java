package com.example.sqldemo.controller;

import com.example.sqldemo.entity.User;
import com.example.sqldemo.server.UserServer;
import com.example.sqldemo.utils.RegisterUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
@CrossOrigin
@RestController
public class UserController {
    //    @Resource
    @Autowired
    private UserServer userServer;

    @RequestMapping(value = "/getAll",method = RequestMethod.GET)
    @ResponseBody
    public List<User> findAllUser(){
        return userServer.findAll();
    }

    @RequestMapping(value = "/get",method = RequestMethod.GET)
    @ResponseBody
    public String findAllUser1(){
        return "helloworld";
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
    public boolean test1(HttpServletRequest req){
        String account = req.getParameter("account");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();
        session.setAttribute("account",account);
        session.setAttribute("password",password);
        session.setAttribute("notLogin",false);
        return userServer.checkLogin(account,password);
    }
    @PostMapping("/register")
    @ResponseBody
    /**
     * 我没有选用@RequestBody Map的形式，因为Map自动拆箱和装箱，性能不好
     */
    public String register(HttpServletRequest req){
        String tel = req.getParameter("tel");
        String password = req.getParameter("password");
        String account = RegisterUser.register(tel);
        if(userServer.register(account,password,tel)){
            return "";
        }
        return account;
    }

}
