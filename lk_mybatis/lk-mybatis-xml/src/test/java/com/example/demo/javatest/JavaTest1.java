package com.example.demo.javatest;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
* 类说明
* @author likui 
* @since  2020年7月16日 上午9:38:51
* @version 1.0
*
*/
public class JavaTest1 {

	public static synchronized String getPrimaryKey(int length) {
		StringBuffer buff = new StringBuffer();
		int lth = 0;
		String strTmp = null;
		while (length > lth) {
			strTmp = UUID.randomUUID().toString();
			strTmp = strTmp.replaceAll("-", "");
			buff.append(strTmp);
			lth += strTmp.length();
		}
		return buff.toString().substring(0, length - 1);
	}
	
	public static void main(String[] args) {
//		String primaryKey = getPrimaryKey(1);
//		System.out.println(primaryKey);
		
//		Random random = new Random();
//		for (int i = 0; i < 100; i++) {
//			int nextInt = random.nextInt(10000);
//			System.out.println(nextInt);
//		}
		
		Map<String,Object> map = new HashMap<>();
		BigDecimal bg = (BigDecimal)map.get("test");
		System.out.println(bg);
	}
}
