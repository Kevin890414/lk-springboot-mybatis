package com.example.demo.io.file;

import java.io.File;

/**
 * 类说明
 * 
 * @author likui
 * @since 2020年8月22日 上午11:21:15
 * @version 1.0
 *
 */
public class FileTest {
	public static void main(String[] args) {
		File file = new File("F:\\myCode\\lk-springboot-mybatis\\lk-springboot-mybatis\\1598066408.png");
		boolean file2 = file.isFile();
		System.out.println(file2);
		
	}
}
