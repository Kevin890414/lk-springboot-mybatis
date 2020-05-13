package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.beans.UserEntity;
import com.example.demo.mybatis.mapper.UserMapper;
import com.example.demo.service.UserService;

/**
* 类说明
* @author likui 
* @since  2020年5月9日 下午3:14:10
* @version 1.0
*
*/
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public List<UserEntity> queryAllUser() {
		List<UserEntity> list = userMapper.getAll();
		return list;
	}

}
