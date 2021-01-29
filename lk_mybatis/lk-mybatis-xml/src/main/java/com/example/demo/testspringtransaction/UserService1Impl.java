package com.example.demo.testspringtransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
* @author  
* @since  2021年1月13日 下午3:56:02
* @version 1.0
*
*/
@Service
@Transactional(propagation=Propagation.NOT_SUPPORTED)
public class UserService1Impl implements UserService1 {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private UserService2 userService2;

	@Override
	public void save(User user) {
		String sql = "insert into user values(?,?)";
		jdbcTemplate.update(sql,
			new Object[]{user.getId(),user.getName()},
			new int[]{java.sql.Types.VARCHAR,java.sql.Types.VARCHAR});
		
		userService2.delete("002");
//		update(user);
//		throw new RuntimeException("error");
		
	}

	@Override
	public void update(User user) {
		String sql = "update user set name = ? where id=?";
		jdbcTemplate.update(sql, new Object[]{user.getName(),user.getId()},
				new int[]{java.sql.Types.VARCHAR,java.sql.Types.VARCHAR});
	}

}
