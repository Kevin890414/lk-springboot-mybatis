package com.example.demo.datetest;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.OffsetDateTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * 类说明
 * 
 * @author likui
 * @since 2020年6月17日 上午8:47:52
 * @version 1.0
 *
 */
public class DateTest01 {

	// 获取今天的日期
	public static void getCurrentDate() {
		LocalDate today = LocalDate.now();
		System.out.println("Today's Local date : " + today);

		// 这个是作为对比
		Date date = new Date();
		System.out.println(date);
	}

	// 获取年、月、日信息
	public static void getDetailDate() {
		LocalDate today = LocalDate.now();
		int year = today.getYear();
		int month = today.getMonthValue();
		int day = today.getDayOfMonth();

		System.out.printf("Year : %d  Month : %d  day : %d ", year, month, day);
	}

	public static void clock() {
		// 根据系统时间返回当前时间并设置为UTC。
		Clock clock = Clock.systemUTC();
		System.out.println("Clock : " + clock);

		// 根据系统时钟区域返回时间
		Clock defaultClock = Clock.systemDefaultZone();
		System.out.println("Clock : " + defaultClock);
	}

	// 如何用Java判断日期是早于还是晚于另一个日期
	public static void isBeforeOrIsAfter() {
		LocalDate today = LocalDate.now();
		LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
		if (tomorrow.isAfter(today)) {
			System.out.println("Tomorrow is " + tomorrow);
			System.out.println("Tomorrow comes after today");
		}

		// 减去一天
		LocalDate yesterday = today.minus(1, ChronoUnit.DAYS);

		if (yesterday.isBefore(today)) {
			System.out.println("Yesterday is " + yesterday);
			System.out.println("Yesterday is day before today");
		}
	}

	// 获取特定时区下面的时间
	public static void getZoneTime() {
		// 设置时区
		ZoneId america = ZoneId.of("America/New_York");

		LocalDateTime localtDateAndTime = LocalDateTime.now();

		ZonedDateTime dateAndTimeInNewYork = ZonedDateTime.of(localtDateAndTime, america);
		System.out.println("现在的日期和时间在特定的时区 : " + dateAndTimeInNewYork);
	}

	// 使用 YearMonth类处理特定的日期
	public static void checkCardExpiry() {
		YearMonth currentYearMonth = YearMonth.now();
		System.out.printf("Days in month year %s: %d%n", currentYearMonth, currentYearMonth.lengthOfMonth());

		YearMonth creditCardExpiry = YearMonth.of(2028, Month.FEBRUARY);
		System.out.printf("Your credit card expires on %s %n", creditCardExpiry);
	}

	public static void zoneOffset() {
		LocalDateTime datetime = LocalDateTime.of(2018, Month.FEBRUARY, 14, 19, 30);
		ZoneOffset offset = ZoneOffset.of("+05:30");
		OffsetDateTime date = OffsetDateTime.of(datetime, offset);
		System.out.println("Date and Time with timezone offset in Java : " + date);
	}

	public static void getTimestamp() {
		Instant timestamp = Instant.now();
		System.out.println("What is value of this instant " + timestamp.toEpochMilli());
	}

	// 使用预定义的格式化工具去解析或格式化日期
	public static void formateDate() {
		String dayAfterTommorrow = "20180210";
		LocalDate formatted = LocalDate.parse(dayAfterTommorrow, DateTimeFormatter.BASIC_ISO_DATE);
		System.out.printf("Date generated from String %s is %s %n", dayAfterTommorrow, formatted);
	}

	public static void main(String[] args) {
		String format = LocalDate.now().format(DateTimeFormatter.ofPattern(("yyyyMMdd")));
		System.out.println(format);
		
		// getCurrentDate();
		// getDetailDate();
		// clock();
		// isBeforeOrIsAfter();
		// getZoneTime();
		// zoneOffset();
//		getTimestamp();
//		formateDate();
//		
//		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd HH:dd:ss");
//
////		LocalDateTime localDateTime1 = LocalDateTime.now();
////        System.out.println("转换前时间1:"+localDateTime1);
////        System.out.println("转换前时间1:"+localDateTime1.format(format));
//        Timestamp time1 = Timestamp.from(Instant.now());
//        System.out.println("转换前时间2:"+time1);
//////       一、java.sql.Timestamp 转 java.time.LocalDateTime 
////        LocalDateTime localDateTime2 = time1.toLocalDateTime();
////        System.out.println("转换后时间3:"+localDateTime2);
////        localDateTime2.format(format);
////        LocalDateTime plusDays = localDateTime2.plusDays(10);
////        System.out.println("加10天后时间:"+plusDays);
////
////        boolean after = plusDays.isAfter(localDateTime2);
////        System.out.println("加10天后时间:"+after);
//        
////       二、java.time.LocalDateTime 转 java.sql.Timestamp 
//        Timestamp time2 = Timestamp.valueOf(LocalDateTime.now());
//        System.out.println("转换后时间4:"+time2);
        
//        long timestamp = System.currentTimeMillis();
//        LocalDate localDate = Instant.ofEpochMilli(timestamp).atZone(ZoneId.systemDefault()).toLocalDate();
//        LocalDateTime localDateTime = Instant.ofEpochMilli(timestamp).atZone(ZoneId.systemDefault()).toLocalDateTime();
//		
//        System.out.println(localDate);
//        System.out.println(localDateTime);
//		使用LocalDateTime计算两个时间的差
//		Duration duration = Duration.between(now,end);
//		long days = duration.toDays(); //相差的天数
//		long hours = duration.toHours();//相差的小时数
//		long minutes = duration.toMinutes();//相差的分钟数
//		long millis = duration.toMillis();//相差毫秒数
//		long nanos = duration.toNanos();//相差的纳秒数
//		
//		String time1 = "2020-07-09 12:45:12";
//		System.out.println(Timestamp.valueOf(time1));
//		String time2 = "2020-07-08";
//		System.out.println(Timestamp.valueOf(time2));
		
//		List<Map<String,String>> list = new ArrayList<>();
//		for (int i = 0; i < 3; i++) {
//			Map<String,String> map = new HashMap<>();
//			map.put("id","id"+i );
//			map.put("name","name"+i );
//			list.add(map);
//		}
//		
//		List<String> collect = list.stream().map(e->e.get("id")).distinct().collect(Collectors.toList());
//		
//		System.out.println(collect);
	}
}
