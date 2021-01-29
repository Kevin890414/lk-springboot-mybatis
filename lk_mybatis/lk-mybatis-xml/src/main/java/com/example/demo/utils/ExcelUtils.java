package com.example.demo.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
* excel工具类
* @author likui 
* @since  2020年12月28日 下午4:17:31
* @version 1.0
*
*/
public class ExcelUtils {
	public static final String ROW_SEPERATOR = "\n";
	public static String xlsx = "xlsx";		// excel2007后的格式
	public static String xls = "xls";		// excel2007前的格式
	
	public static Map<String,Object> exportExcel(Map<String,Object> context,Map<String,Object> dataMap){
		
		String exportFileName = (String) dataMap.get("exportFileName");
		String[] titleKeys = (String[] ) dataMap.get("titleKeys");
		String[] titleTexts = (String[] ) dataMap.get("titleTexts");
		List<Map<String,Object>> dataList = (List<Map<String,Object>>) dataMap.get("dataList");
		String fileType = null;
		Workbook workbook = null;
		
		try {
			// 根据文件格式(2003或者2007)来初始化
			if (exportFileName.endsWith(xlsx)) {
				workbook = new XSSFWorkbook();
				fileType = xlsx;
			} else if (exportFileName.endsWith(xls)) {
				workbook = new HSSFWorkbook();
				fileType = xls;
			}
			Sheet sheet = workbook.createSheet();
			
			writeExcelTitle(workbook,sheet,titleTexts,fileType);
			
			
			
		} catch (Exception e) {
			// 错误信息
			throw e;
		} finally {
			try {
				if (workbook != null) {
					workbook.close();
				}
			} catch (IOException e) {
				System.out.println("读取excel数据文件失败");
			}
		}
		return null;
	}

	private static void writeExcelTitle(Workbook workbook, Sheet sheet, String[] titleTexts, String fileType) {
		//在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		Row title = sheet.createRow(0);
		Font font = workbook.createFont();
		font.setFontName("黑体");
		font.setFontHeightInPoints((short)12);
//		font.setBold(bold);
		CellStyle headStyle = workbook.createCellStyle();
		headStyle.setFont(font);
//		headStyle.setAlignment(HorizontalAlignment.);
//		headStyle.setVerticalAlignment(align);
		
	}
	

}
