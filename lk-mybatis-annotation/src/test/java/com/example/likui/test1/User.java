package com.example.likui.test1;
/**
* 类说明
* @author likui 
* @since  2020年5月19日 下午6:20:15
* @version 1.0
*
*/
public class User {
	
	private String userId;
	private String userName;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + "]";
	}
	

}
