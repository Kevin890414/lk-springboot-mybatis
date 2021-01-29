package com.example.demo.testspringtransaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* @author 
* @since  2021年1月13日 下午3:36:34
* @version 1.0
* 
*  	@Data  ：注解在类上；提供类所有属性的 getting 和 setting 方法，此外还提供了equals、canEqual、hashCode、toString 方法
*	@Setter：注解在属性上；为属性提供 setting 方法
*	@Getter：注解在属性上；为属性提供 getting 方法
*	@Log4j ：注解在类上；为类提供一个 属性名为log 的 log4j 日志对象
*	@NoArgsConstructor：注解在类上；为类提供一个无参的构造方法
*	@AllArgsConstructor：注解在类上；为类提供一个全参的构造方法
*
*/
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class User {
	
	private String id;
	private String name;
	
	public User(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + "]";
	}

}
