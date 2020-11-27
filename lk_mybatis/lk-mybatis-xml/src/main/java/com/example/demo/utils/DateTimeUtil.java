package com.example.demo.utils;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
	public static final String DEF_YM1 = "yyyy-MM";
	public static final String DEF_YM2 = "yyyy/MM";
	public static final String DEF_TIME1 = "HHmmss";
	public static final String DEF_TIME2 = "HH:mm:ss";
	public static final String DEF_TIME3 = "HH时mm分ss秒";
	public static final String DEF_DATE = "yyyyMMdd";
	public static final String DEF_FOR_DATE1 = "yyyy-MM-dd";
	public static final String DEF_FOR_DATE2 = "yyyy/MM/dd";
	public static final String DEF_DATE_TIME = "yyyyMMddHHmmss";
	public static final String DEF_FOR_DATE_TIME1 = "yyyy-MM-dd HH:mm:ss";
	public static final String DEF_FOR_DATE_TIME2 = "yyyy/MM/dd HH:mm:ss";
	public static final String DEF_FOR_DATE_TIME3 = "yyyy年MM月dd日 HH时mm分ss秒";
	public static final String DEF_DATE_TIMES = "yyyyMMddHHmmssSSS";
	static {
		DT = DateTimeStyle.defForDate;
		TIME = Timestamp.valueOf(LocalDateTime.now());
	}
	
	/**   
	 * @Title: getDateTimeFormat  
	 * @Description: 获取时间字符串格式
	 * @param: @param dateTime
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: String      
	 * @throws   
	 */  
	public static String getDateTimeFormat(String dateTime) throws Exception {
		
		String format = null;
		
		if (dateTime == null) {
			throw new Exception("dateTime时间格式有误");
		}
		
		int length = dateTime.length();
		switch(length) {
			case 7:
				if (dateTime.contains("-")) {
					format = DEF_YM1;
				} else if (dateTime.contains("/")) {
					format = DEF_YM2;
				}
				break;
			case 8:
				format = DEF_DATE;
				break;
			case 9:
				if (dateTime.contains("-")) {
					format = DEF_FOR_DATE1;
				} else if (dateTime.contains("/")) {
					format = DEF_FOR_DATE2;
				}
				break;
			case 14:
				format = DEF_DATE_TIME;
				break;
			case 19:
				if (dateTime.contains("-")) {
					format = DEF_FOR_DATE_TIME1;
				} else if (dateTime.contains("/")) {
					format = DEF_FOR_DATE_TIME2;
				}
				break;
			case 17:
				format = DEF_DATE_TIMES;
				break;
			case 21:
				format = DEF_FOR_DATE_TIME3;
				break;
		}
		
		if (format == null) {
			throw new Exception("dateTime时间格式为空");
		}
		
		return format;
	}
	
	/**   
	 * @Title: getIntervalDays   
	 * @Description: 获取日期间隔天数（不能跨月，最多30天）、月数（不能跨年，最多11个月）、年数。
	 * @param: @param startDate
	 * @param: @param endDate
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: Period      
	 * @throws   
	 */  
	public static Period getIntervalDays(LocalDate startDate,LocalDate endDate) throws Exception {
		
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
	 * @param: @param unit 可以是ChronoUnit的任意时间单位(年、月、日、时、分、秒、)
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
	
	/**
	 * @throws Exception    
	 * @Title: getCurrentDayStartTime   
	 * @Description: 获取当日的起始、结束时间 
	 * @param: @param dateTime
	 * @param: @param localTime
	 * @param: @return      
	 * @return: Timestamp      
	 * @throws   
	 */  
	public static Timestamp getCurrentDayStartTime(String dateTime,LocalTime localTime) throws Exception {
		
		String dateTimeFormat = getDateTimeFormat(dateTime);
		
		LocalDateTime parse = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(dateTimeFormat));
		LocalDateTime with = parse.with(localTime);
		
		return Timestamp.valueOf(with);
	}
	
	public static Timestamp stringToTimestamp(String dateTime) throws Exception {
		
		String dateTimeFormat = getDateTimeFormat(dateTime);
		
		LocalDateTime parse = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(dateTimeFormat));
		
		return Timestamp.valueOf(parse);
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
		
//		DateTimeStyle[] values = DateTimeStyle.values();
//		System.out.println(values);
//		for (DateTimeStyle dateTimeStyle : values) {
//			System.out.println(dateTimeStyle);
//		}
		
//		String dateTime = "20201003";
//		Timestamp currentDayStartTime = stringToTimestamp(dateTime);
//		Timestamp currentDayStartTime2 = stringToTimestamp(dateTime);
//		
//		System.out.println(currentDayStartTime);
//		System.out.println(currentDayStartTime2);
		
//		LocalDateTime minusDays = LocalDateTime.now().minusDays(-1);
//		System.out.println(minusDays);
		
		List<Map<String,Object>> list1=new ArrayList<>();
		list1.add(UtilMisc.toMap("partyId", "100001","gender","1"));
		list1.add(UtilMisc.toMap("partyId", "100002","gender","2"));
		List<Map<String,Object>> list2=new ArrayList<>();
		list2.add(UtilMisc.toMap("partyId", "100001","belongOrgId","CBI000000"));
		list2.add(UtilMisc.toMap("partyId", "100002","belongOrgId","CBI000001"));
		list1.addAll(list2);
		
		// 不想覆盖，保留最初的值： 
		list1.stream().flatMap(m -> m.entrySet().stream())
		              .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> b)); 
		 
		
		System.out.println(list1);
	}

}
