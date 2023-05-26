package com.project.campustaobao.controler;
import com.project.campustaobao.server.UserServer;
import com.project.campustaobao.utils.RegisterUser;
import com.project.campustaobao.vo.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class LoginRegisterController {
    @Autowired
    private UserServer userServer;
    @GetMapping("/c2")
    public String test1(){
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
    public boolean login(HttpServletRequest req){
        String account = req.getParameter("account");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();
        session.setAttribute("account",account);
        session.setAttribute("password",password);
        session.setAttribute("notLogin",false);
        return userServer.login(account,password);
    }
    @PostMapping("/register")
    @ResponseBody
    /**
     * 我没有选用@RequestBody Map的形式，因为Map自动拆箱和装箱，性能不好
     */
    public ResultMessage<String> register(String tel, String password){
        if(!userServer.register(password,tel)){
            return new ResultMessage<>(ResultMessage.ERROR_CODE,"同一个账号不能多次注册","false");
        }
        return new ResultMessage<>(ResultMessage.SUCCESS_CODE,"恭喜你注册成功",RegisterUser.generateAccount(tel));
    }
}
