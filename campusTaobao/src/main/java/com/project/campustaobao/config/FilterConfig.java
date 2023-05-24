package com.project.campustaobao.config;

import com.project.campustaobao.filter.MyFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置过滤器
 */
@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean registerMyFilter(){
        FilterRegistrationBean<MyFilter> bean = new FilterRegistrationBean<>();
        bean.setOrder(1);
        bean.setFilter(new MyFilter());
        // 匹配"/hello/"下面的所有url
        bean.addUrlPatterns("/*");
        return bean;
    }
//    @Bean
//    public FilterRegistrationBean registerMyAnotherFilter(){
//        FilterRegistrationBean<MyFilter> bean = new FilterRegistrationBean<>();
//        bean.setOrder(2);
//        bean.setFilter(new MyFilter());
//        // 匹配所有url
//        bean.addUrlPatterns("/*");
//        return bean;
//    }
}
