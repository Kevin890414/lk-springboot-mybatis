package com.example.likui.test1;
/**
* 类说明
* @author likui 
* @since  2020年5月19日 下午6:20:23
* @version 1.0
*
*/
public class Role {
	
	private String roleId;
	private String roleName;
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", roleName=" + roleName + "]";
	}
	
	
	

}
