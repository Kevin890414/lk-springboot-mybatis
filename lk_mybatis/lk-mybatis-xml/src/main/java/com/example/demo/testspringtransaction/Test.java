package com.example.demo.testspringtransaction;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
* @author 
* @since  2021年1月13日 下午4:07:42
* @version 1.0
*
*/
public class Test {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(JdbcConfig.class);
		UserService1 userService1 = ac.getBean(UserService1.class);
		User user = new User("001","唐僧");
		userService1.save(user);
	}

}
