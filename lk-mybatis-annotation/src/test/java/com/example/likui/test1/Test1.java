package com.example.likui.test1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* 类说明
* @author likui 
* @since  2020年5月14日 下午2:14:16
* @version 1.0
*
*/
public class Test1 {
	
	private static final Logger logger = LoggerFactory.getLogger(Test1.class);
	
	public static void main(String[] args) {
		// 日志
//		logger.debug("用户姓名{},电话{},生日{},年龄{},性别{}","唐僧","13212311231","19890414","21","男");
		
		User user = new User();
		user.setUserId("001");
		user.setUserName("001测试用户");
		
		Role role = new Role();
		role.setRoleId("001");
		role.setRoleName("001测试角色");
		
		MessageReq<User> userReq = new MessageReq<User>();
		userReq.setRequestBody(user);
//		userReq.setMsgId("user001");
//		userReq.setMsgName("測試用戶");
		installHead(userReq);
		
		System.out.println("用户"+userReq);
		
		MessageReq<Role> roleReq = new MessageReq<Role>();
		roleReq.setRequestBody(role);
//		roleReq.setMsgId("role001");
//		roleReq.setMsgName("測試角色");
		
		installHead(roleReq);
		System.out.println("角色"+roleReq);
		
		
		
		
		
		
	}

	private static <T> void installHead(MessageReq<T> msg) {
		msg.setMsgId("001");
		msg.setMsgName("001请求");
	}
	
	
	

}
