package com.example.demo.service;

import java.util.Arrays;
import java.util.List;

import com.example.demo.beans.UserEntity;

/**
* 类说明
* @author likui 
* @since  2020年5月9日 下午3:12:20
* @version 1.0
*
*/
public interface UserService {
	
	public List<UserEntity> queryAllUser();
	

	public static void main(String[] args) {
		String str = "1|2|3|4||";
		String[] split = str.split("\\|",10);
		List<String> list = Arrays.asList(split);
		System.out.println(list);
	}
	
}
