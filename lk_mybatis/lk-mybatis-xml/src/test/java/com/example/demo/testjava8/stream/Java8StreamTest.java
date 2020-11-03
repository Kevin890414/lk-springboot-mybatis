package com.example.demo.testjava8.stream;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 类说明
 * 
 * @author likui
 * @since 2020年8月18日 下午2:57:02
 * @version 1.0
 *
 */
public class Java8StreamTest {
	public static void main(String[] args) {
		FileInputStream inputStream = null;
		ByteArrayOutputStream byteOut = null;
		try {
			inputStream = new FileInputStream("");
			byteOut = new ByteArrayOutputStream();
			
			byte[] bt = new byte[1024];
			
			for (int i = inputStream.read(bt); i != -1;) {
				byteOut.write(bt, 0, i);
			}
			
			byteOut.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
				if (byteOut != null) {
					byteOut.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
