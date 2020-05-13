package com.example.demo.enums;

/**
 * 性别枚举
 * 
 * @author likui
 * @since 2020年5月9日 下午2:21:37
 * @version 1.0
 *
 */
public enum UserSexEnum {

	MAN("男"), WOMAN("女");

	private final String name;

	private UserSexEnum(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
