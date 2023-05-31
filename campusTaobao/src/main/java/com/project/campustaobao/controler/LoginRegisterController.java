package com.project.campustaobao.controler;
import com.project.campustaobao.server.UserServer;
import com.project.campustaobao.utils.RegisterUser;
import com.project.campustaobao.utils.Request;
import com.project.campustaobao.utils.Session;
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
    /*
      我没有选用@RequestBody Map的形式，因为Map自动拆箱和装箱，性能不好
     */
    public ResultMessage<Integer> login(){
        HttpServletRequest req = (HttpServletRequest) Request.getRequest();
        HttpSession session = req.getSession();
        //设置session最大存活时间5小时
        session.setMaxInactiveInterval(5*3600);
        String account = req.getParameter("account");
        String password = req.getParameter("password");
        Session.setSessionAttribute(req, "account",account);
        Session.setSessionAttribute(req,"password",password);
        Session.setSessionAttribute(req,"notLogin",false);
        int login = userServer.login(account,password);
        ResultMessage<Integer> resultMessage;
        if(login == 0){
            resultMessage = new ResultMessage<>(ResultMessage.SUCCESS_CODE,
                    "成功登录",login);
        }else if(login == 1){
            resultMessage = new ResultMessage<>(ResultMessage.ERROR_CODE,
                    "账号已经被禁用",login);
        }else{
            resultMessage = new ResultMessage<>(ResultMessage.ERROR_CODE,
                    "请检查你的账号和密码是否正确",login);
        }
        return resultMessage;
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
