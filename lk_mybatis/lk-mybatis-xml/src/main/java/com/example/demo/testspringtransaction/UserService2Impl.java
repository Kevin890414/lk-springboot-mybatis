package com.example.demo.testspringtransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
* 类说明
* @author likui 
* @since  2021年1月13日 下午3:56:02
* @version 1.0
*
*/
@Service
@Transactional(propagation=Propagation.NESTED)
public class UserService2Impl implements UserService2 {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void delete(String id) {
		String sql = "delete from user where id=?";
		jdbcTemplate.update(sql, id);
	}

}
