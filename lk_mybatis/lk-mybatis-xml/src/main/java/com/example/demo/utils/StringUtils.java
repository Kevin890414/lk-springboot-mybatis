package com.example.demo.utils;

import java.util.UUID;

/**
* 字符串工具类
* @author likui 
* @since  2020年11月3日 下午4:02:26
* @version 1.0
*
*/
public class StringUtils {
	
	/**   
	 * @Description: 同步方法，获取指定长度的随机字符串
	 * @Title: getPrimaryKey   
	 * @param: @param length
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String getSpecifyLengthUuid(int length) {
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

}
