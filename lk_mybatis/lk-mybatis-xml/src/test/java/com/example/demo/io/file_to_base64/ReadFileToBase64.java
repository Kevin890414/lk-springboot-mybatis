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
* @author likui 
* @since  2020年8月6日 下午8:35:03
* @version 1.0
*
*/
public class ReadFileToBase64 {
	
	public static String readFileToBase64(String filePath) {
		String str = "";
		try {
			byte[] readAllBytes = Files.readAllBytes(Paths.get(filePath));
			str = Base64.getEncoder().encodeToString(readAllBytes);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;

	}
	
	public static String base64ToFile(String base64,String filePath) {
		
		if (base64 == null && filePath == null) {
            return "生成文件失败，请给出相应的数据。";
		}
		try {
			Files.write(Paths.get(filePath), Base64.getDecoder().decode(base64),StandardOpenOption.CREATE);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "指定路径下生成文件成功！";

	}
	
	public static void main(String[] args) {
		String inputFilePath = "F:/myCode/IoTestPath/1234568.txt";
		String readFileToBase64 = readFileToBase64(inputFilePath);
		System.out.println("readFileToBase64: \r\n"+readFileToBase64);
		
		String outputFilePath = "F:/myCode/IoTestPath/1234567.txt";
		base64ToFile(readFileToBase64, outputFilePath);
	}

}
