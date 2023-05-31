package com.project.campustaobao.server;

import com.project.campustaobao.pojo.Administrator;


import java.util.List;
import java.util.Map;

public interface AdminServer {
    boolean isBaned(String account);
    Administrator queryAdminByAccount(String adminAccount);
    List<Administrator> queryAdminList();
    boolean login(String account,String password);
    Map<String,String> queryAdminSimpleInfo(String AdminAccount);
    boolean updateAdminSimpleInfo(Map<String,String> adminSimpleInfo);
    Administrator insertAdmin(Administrator admin);

    boolean updateAdminStatus(String account,boolean statues);
    boolean forbidAdmin(String adminAccount);
    boolean notForbidAdmin(String adminAccount);
}
