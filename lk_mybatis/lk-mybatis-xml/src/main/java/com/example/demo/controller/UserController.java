package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
	public List<UserEntity> queryAllUserList(HttpServletRequest request) {
		List<UserEntity> list = userService.queryAllUser();
		
		log.info("查询结果"+list);
		
		HttpSession session = request.getSession();
		session.getId();
		log.info("页面1sessionId"+session.getId());
		
		return list;
	}

	
	
	public static void main(String[] args) {
		System.out.println(Math.round(-1.63f));
		System.out.println(Math.round(1.53d));
		System.out.println(Math.round(1.63f));
		System.out.println(Math.random());
		System.out.println(Math.random()*1000);
		
	}
}
