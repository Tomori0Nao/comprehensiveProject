package com.project.campustaobao.controler;

import com.project.campustaobao.utils.Request;
import com.project.campustaobao.utils.Session;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class TestSession {
    @GetMapping("/account")
    @ResponseBody
    public String getSessionInfo(){
        HttpServletRequest req = (HttpServletRequest) Request.getRequest();
        String account = (String)Session.getSessionAttribute(req,"account");
        String password = (String)Session.getSessionAttribute(req,"password");
        return "account = "+account +"password = "+
                password;

    }
}
