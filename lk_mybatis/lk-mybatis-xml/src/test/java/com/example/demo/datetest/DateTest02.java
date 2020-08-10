package com.example.demo.datetest;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * 时间、字符串转换
 * 
 * @author likui
 * @since 2020年6月17日 上午8:47:52
 * @version 1.0
 *
 */
public class DateTest02 {

	/**
	 * 
	 * 日期字符串yyyy-MM-dd HH:mm:ss 转Timestamp
	 */
	public static Timestamp stringToTimestamp(String timeStr) {
		DateTimeFormatter ofPattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime parse = LocalDateTime.parse(timeStr, ofPattern);
		Timestamp valueOf = Timestamp.valueOf(parse);
		return valueOf;
	}
	
	/**
	 * 
	 * 时间 转日期字符串yyyy-MM-dd HH:mm:ss  
	 */
	public static String timeToStr() {
		//获得当前时间
	    LocalDateTime ldt = LocalDateTime.now();
	    // 格式化日期时间类型为字符串
	    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
	    String format = ldt.format(dtf);
	    return format;
	}
	
	/**
	 * 
	 * 时间 转日期字符串yyyy-MM-dd HH:mm:ss  
	 */
	public static String dateToStr1() {
		//获得当前时间
		LocalDate now = LocalDate.now();
		// 格式化日期时间类型为字符串
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
		String format = now.format(dtf);
		return format;
	}
	
	/**
	 * 
	 * 时间 转日期字符串yyyy-MM-dd HH:mm:ss  
	 */
	public static String timestampToStr1() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		// Timestamp 转LocalDateTime
		LocalDateTime ldt = LocalDateTime.ofInstant(timestamp.toInstant(), ZoneId.systemDefault());
		// 格式化日期时间类型为字符串
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
		String format = ldt.format(dtf);
		return format;
	}
	/**
	 * 
	 * 时间 转日期字符串yyyy-MM-dd HH:mm:ss  
	 */
	public static String timestampToStr2() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
		String format2 = format.format(timestamp);
		return format2;
	}
	/**
	 * 
	 * string 转 Timestamp
	 * @throws ParseException 
	 */
	public static Timestamp strToTimestamp() throws ParseException {
		String strTime = "20200801121234";
		DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		Timestamp timstamp = new Timestamp(format.parse(strTime).getTime());

		return timstamp;
	}
	
	/**
	 * 
	 * java.sql.date 转String
	 */
	public static String dateToStr2() {
		// 获得当前时间
		Date date = Date.valueOf(LocalDate.now());
		// 格式化日期时间类型为字符串
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String format = sdf.format(date);
        System.out.println("Date 转 String: " + format);
		return format;
	}
	
	/**
	 * 
	 * 日期字符串yyyyMMdd 转Timestamp
	 */
	public static Date stringToDate(String dateStr) {
		LocalDate parse = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyyMMdd"));
		return Date.valueOf(parse);
	}
	
	public static void main(String[] args) throws ParseException {
		String timeStr = "2020-08-01 12:00:00";
		Timestamp stringToTimestamp = stringToTimestamp(timeStr);
		System.out.println(stringToTimestamp);
		
		String dateStr = "20200801";
		Date stringToDate = stringToDate(dateStr);
		System.out.println(stringToDate);
		
		String timeToStr = timeToStr();
		System.out.println(timeToStr);
		
		String dateToStr = dateToStr1();
		System.out.println(dateToStr);
		
		String dateToStr2 = dateToStr2();
		System.out.println(dateToStr2);
		
		String timestampToStr = timestampToStr2();
		System.out.println(timestampToStr);
		Timestamp strToTimestamp = strToTimestamp();
		System.out.println(strToTimestamp);
		
	}
	
	
}
