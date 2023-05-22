package com.example.sqldemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

//通过使用@MapperScan可以指定要扫描的Mapper类的包的路径,同时,使用@MapperScan注解多个包
@SpringBootApplication
//扫描过滤器
//@ServletComponentScan
@MapperScan(value = {"com.example.*.dao","com.example.*.mapper"})
public class SqldemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SqldemoApplication.class, args);
    }

}
