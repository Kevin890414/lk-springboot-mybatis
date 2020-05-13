package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.beans.UserEntity;
import com.example.demo.service.UserService;

/**
* 类说明
* @author likui 
* @since  2020年5月9日 下午3:19:08
* @version 1.0
*
*/
@RestController
public class UserController {
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	
	@RequestMapping("queryAll")
	public List<UserEntity> queryAllUserList() {
		List<UserEntity> list = userService.queryAllUser();
		
		return list;
	}

	
	
	public static void main(String[] args) {
		String str1 = "123";
		String str2 = "123";
		String str3 = new String("123");
		String str4 = new String("123");
		System.out.println(str1==str2);
		System.out.println(str1==str3);
		System.out.println(str1.equals(str2));
		System.out.println(str1.equals(str3));
		System.out.println(str3.equals(str4));
	}
}
