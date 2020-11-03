package com.example.demo.utils;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalUnit;

/**
* 类说明
* @author likui 
* @since  2020年10月22日 下午1:46:07
* @version 1.0
*
*/
public class DateTimeUtil {
	
	public static final DateTimeStyle DT;
	public static final Timestamp TIME;
	public static final String DEF_YM = "yyyy-MM";
	public static final String DEF_TIME = "HHmmss";
	public static final String DEF_DATE = "yyyyMMdd";
	public static final String DEF_FOR_DATE = "yyyy-MM-dd";
	public static final String DEF_DATE_TIME = "yyyyMMddHHmmss";
	public static final String DEF_FOR_DATE_TIME = "yyyy-MM-dd HH:mm:ss";
	public static final String DEF_DATE_TIMES = "yyyyMMddHHmmssSSS";
	static {
		DT = DateTimeStyle.defForDate;
		TIME = Timestamp.valueOf(LocalDateTime.now());
	}
	
	/**   
	 * @Title: getDateIntervalDays   
	 * @Description: 获取日期间隔天数（不能跨月，最多30天）、月数（不能跨年，最多11个月）、年数。
	 * @param: @param startDate
	 * @param: @param endDate
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: Period      
	 * @throws   
	 */  
	public static Period getDateIntervalDays(LocalDate startDate,LocalDate endDate) throws Exception {
		
		if (startDate == null || endDate == null) {
			throw new Exception("startDate、endDate不能为空");
		}
		
		Period between = Period.between(startDate, endDate);
		
		return between;
	}
	
	/**   
	 * @Title: getIntervalUnitTime   
	 * @Description: 获取指定单位的间隔时间，根据自然时间算
	 * @param: @param startTime 
	 * @param: @param endTime
	 * @param: @param unit 可以是ChronoUnit的任意时间单位
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: long      
	 * @throws   
	 */  
	public static long getIntervalUnitTime(Temporal startTime,Temporal endTime,TemporalUnit unit) throws Exception {
		
		if (startTime == null || endTime == null || unit == null) {
			throw new Exception("startDate、endDate、unit不能为空");
		}
		
		long until = startTime.until(endTime, unit);
		
		return until;
	}
	
	public static void main(String[] args) throws Exception {
//		LocalDateTime startDate = LocalDateTime.parse("2020-10-29 12:00:01", DateTimeFormatter.ofPattern(DEF_FOR_DATE_TIME));
//		LocalDateTime endDate = LocalDateTime.parse("2020-10-30 11:00:00", DateTimeFormatter.ofPattern(DEF_FOR_DATE_TIME));
//		long days = getIntervalUnitTime(startDate.toLocalDate(), endDate.toLocalDate(),ChronoUnit.DAYS);
//		long months= getIntervalUnitTime(startDate.toLocalDate(), endDate.toLocalDate(),ChronoUnit.MONTHS);
//		long years = getIntervalUnitTime(startDate.toLocalDate(), endDate.toLocalDate(),ChronoUnit.YEARS);
//		System.out.println(days);
//		System.out.println(months);
//		System.out.println(years);
		
		DateTimeStyle[] values = DateTimeStyle.values();
		System.out.println(values);
		for (DateTimeStyle dateTimeStyle : values) {
			System.out.println(dateTimeStyle);
		}
	}

}
