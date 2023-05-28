package com.project.campustaobao.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletRequest;

/**
 * 为什么我要多此一举，单独写一个类来获取ServletRequest对象?
 * 原因：
 * 1.我不想在controller的方法中传入形参ServletRequest
 * 2.我也不想在Controller中添加属性ServletRequest,然后加上@Autowired自动装配
 *
 */
public class Request {
    public static ServletRequest getRequest(){
        ServletRequestAttributes requestAttributes =
                ServletRequestAttributes.class.cast(
                        RequestContextHolder.getRequestAttributes());
        return requestAttributes.getRequest();
    }

}
