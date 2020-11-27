package com.example.demo.controller.test.base64;


import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* 类说明
* @author likui 
* @since  2020年9月17日 下午5:36:35
* @version 1.0
*
*/
@RestController
public class Base64Test {
	
	
	@PostMapping("inputBase64ToStream")
	public String inputBase64ToSendStream(HttpServletRequest request) {
		
		String base64 = (String) request.getAttribute("base64");
		
		
		
		
		return null;
		
	}
	
	public static void main(String[] args) {
//		String[] str = {"123","456","121232133","4444444"};
//		List<String> asList = new LinkedList<String>();
//		asList.add(str[0]);
//		asList.add(str[1]);
//		asList.add(str[2]);
//		asList.add(str[3]);
//		int pageNum = 2;
//		int pageSize = 3;
//		
//		List<String> collect = asList.stream().skip((pageNum-1)*pageSize).limit(pageSize).collect(Collectors.toList());
//		System.out.println(collect);
		
//		String str = "142724198903091011";
//		// 正则表达式
//		String idenNumRule18 = "^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$";
//		// 15 位
//		String idenNumRule15 = "^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{2}$";
//		String idenNumRule = "^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{2}$";
//		
//		
//		String str1 = "13759727861";
//		String teleRegex = "^1[3-9][0-9]{9}";
//		
//		System.out.println(str1.matches(teleRegex));
		
		LocalDateTime now = LocalDateTime.now();
		System.out.println(now);
		LocalDateTime minusDays = now.minusDays(450);
		System.out.println(now);
		System.out.println(minusDays);
	}
	

}
