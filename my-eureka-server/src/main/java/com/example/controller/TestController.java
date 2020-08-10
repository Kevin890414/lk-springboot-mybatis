package com.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* 类说明
* @author likui 
* @since  2020年6月15日 下午6:06:28
* @version 1.0
*
*/
@RestController
public class TestController {
	
	@RequestMapping("test1")
	public String test1() {
		return "test1";
	}

}
