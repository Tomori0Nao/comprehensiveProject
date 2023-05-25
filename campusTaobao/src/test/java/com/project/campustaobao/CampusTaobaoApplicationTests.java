package com.project.campustaobao;

import com.project.campustaobao.server.UserServer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CampusTaobaoApplicationTests {
	@Autowired
	private UserServer userServer;
	@Test
	public void checkLogin(){
		String account = "111";
		String password = "2223";
		System.out.println(userServer.login(account,password));
	}

}
