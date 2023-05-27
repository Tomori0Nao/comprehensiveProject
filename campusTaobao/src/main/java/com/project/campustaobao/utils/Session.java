package com.project.campustaobao.utils;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 封装好的session
 * 便于修改和更新属性
 */
public class Session {
    public static Object getSessionAttribute(ServletRequest req,String name){
        HttpSession session = ((HttpServletRequest)req).getSession();
        return session.getAttribute(name);
    }
    public static void setSessionAttribute(ServletRequest req, String name, Object value){
        HttpSession session = ((HttpServletRequest)req).getSession();
        session.setAttribute(name,value);
    }
}
