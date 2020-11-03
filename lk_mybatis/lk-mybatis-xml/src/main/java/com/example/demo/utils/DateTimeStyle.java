package com.example.demo.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
* 类说明
* @author likui 
* @since  2020年10月29日 上午9:46:10
* @version 1.0
*
*/
public enum DateTimeStyle {
	defTime("HHmmss"),
	defDate("yyyyMMdd"),
	defForDate("yyyy-MM-dd"),
	defDateTime("yyyyMMddHHmmss"),
	defForDateTime("yyyy-MM-dd HH:mm:ss"),
	defDateTimes("yyyyMMddHHmmssSSS");
	
	private int length;
	public int getLength() {
		return length;
	}

	public String getFormat() {
		return format;
	}

	public DateFormat getDateFormat() {
		return dateFormat;
	}

	private String format;
	private DateFormat dateFormat;
	
	private DateTimeStyle(String format) {
		this.length = format.length();
		this.format = format;
		this.dateFormat = new SimpleDateFormat(format);
	}
	
	@Override
	public String toString() {
		return this.format;
	}

}
