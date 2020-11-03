package com.example.demo.javatest;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

/**
* 类说明
* @author likui 
* @since  2020年7月16日 上午9:38:51
* @version 1.0
*
*/
public class JavaTest2 {

	/**   
	 * @Description: 同步方法，获取指定长度的随机字符串
	 * @Title: getPrimaryKey   
	 * @param: @param length
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
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
	
	public static void testDoWhile() {
		
		int pageNum = 1;
		int pageSize=50;
		int total = 101;
		
		do {
			
			System.out.println("总数："+ total+",当前查询数量："+pageNum*pageSize);
			
			pageNum++;
			
		} while(total > pageNum*pageSize);
		
		System.out.println("总数："+ total+",当前查询数量："+pageNum*pageSize);
	}
	
	public static void main(String[] args) {
//		String primaryKey = getPrimaryKey(1);
//		System.out.println(primaryKey);
		
//		Random random = new Random();
//		for (int i = 0; i < 100; i++) {
//			int nextInt = random.nextInt(10000);
//			System.out.println(nextInt);
//		}
		
//		Map<String,Object> map = new HashMap<>();
//		BigDecimal bg = (BigDecimal)map.get("test");
//		System.out.println(bg);
		
//		testDoWhile();
//		Map<String,String> map = new HashMap<>();
//		map.put("123", "456");
//		
//		System.out.println(map.remove("123"));
		
		
		Timestamp time = Timestamp.from(Instant.now());
		
		System.out.println(time);
//		System.out.println(replace);
	}
}
