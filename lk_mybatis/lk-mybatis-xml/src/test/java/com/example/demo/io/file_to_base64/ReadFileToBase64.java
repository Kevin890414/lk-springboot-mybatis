package com.example.demo.io.file_to_base64;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Base64;

/**
 * 读取文件转base64
 * 
 * @author likui
 * @since 2020年8月6日 下午8:35:03
 * @version 1.0
 *
 */
public class ReadFileToBase64 {

	public static String readFileToBase64(String filePath) {
		String str = "";
		byte[] readAllBytes = null;
		try {
			readAllBytes = Files.readAllBytes(Paths.get(filePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		str = Base64.getEncoder().encodeToString(readAllBytes);
		return str;

	}

	/**   
	 * @Title: fileTobase64_2   
	 * @Description: 文件转base64
	 * @param: @param filePath 带文件名全路径
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String fileTobase64_2(String filePath) {

		FileInputStream fileForInput = null;
		try {
			fileForInput = new FileInputStream(filePath);
			byte[] bytes = new byte[fileForInput.available()];
			fileForInput.read(bytes);

			String content = Base64.getEncoder().encodeToString(bytes); // 具体的编码方法
			System.out.println(content);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fileForInput != null) {
				try {
					fileForInput.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return "指定路径下生成文件成功！";

	}

	public static void main(String[] args) {
		String inputFilePath = "F:/myCode/IoTestPath/2019年10月08号工资单.png";
		// 文件转base64
//		String readFileToBase64 = readFileToBase64(inputFilePath);
//		System.out.println("readFileToBase64: \r\n" + readFileToBase64);
		
		String fileTobase64_2 = fileTobase64_2(inputFilePath);
		System.out.println("fileTobase64_2: \r\n" + fileTobase64_2);

		String outputFilePath = "F:/myCode/IoTestPath/1234567890.png";
		Base64ToFile.base64ToFile(fileTobase64_2, outputFilePath);
	}

}
