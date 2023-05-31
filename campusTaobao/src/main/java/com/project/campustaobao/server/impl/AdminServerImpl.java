package com.project.campustaobao.server.impl;

import com.project.campustaobao.mapper.AdminMapper;
import com.project.campustaobao.pojo.Administrator;
import com.project.campustaobao.server.AdminServer;
import com.project.campustaobao.utils.Request;
import com.project.campustaobao.utils.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Service
public class AdminServerImpl implements AdminServer {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public boolean isBaned(String account) {
        return adminMapper.isBaned(account);
    }

    /**
     * 通过管理员账号获取管理员信息
     * @param adminAccount 管理员账号
     * @return 管理员信息
     */
    @Override
    public Administrator queryAdminByAccount(String adminAccount){
        return adminMapper.queryAdminByAccount(adminAccount);
    }
    /**
     * 此方法用于获取管理员集合，
     * 主要是由系统管理员调用
     * @return 普通管理员的集合
     */
    @Override
    public List<Administrator> queryAdminList(){
        return adminMapper.queryAdminList(0);
    }
    @Override
    public boolean login(String account,String password){
        String  p = adminMapper.queryPasswordAccount(account);
        if(p == null || !p.equals(password)){
            return false;
        }
        //登录成功则将管理员的账号密码信息存储到session中
        HttpServletRequest req = (HttpServletRequest) Request.getRequest();
        Session.setSessionAttribute(req,"adminAccount",account);
        Session.setSessionAttribute(req,"adminPassword",password);
        return true;
    }

    @Override
    public Map<String, String> queryAdminSimpleInfo(String AdminAccount) {
        return null;
    }
}
