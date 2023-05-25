package com.project.campustaobao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan(value = {"com.project.campustaobao.mapper"})
public class CampusTaobaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CampusTaobaoApplication.class, args);
	}

}
