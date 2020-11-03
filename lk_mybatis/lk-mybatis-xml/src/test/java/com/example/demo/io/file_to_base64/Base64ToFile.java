package com.example.demo.io.file_to_base64;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Base64;

/**
 * base64字符串转 文件
 * 
 * @author likui
 * @since 2020年8月10日 下午7:38:36
 * @version 1.0
 *
 */
public class Base64ToFile {

	/**
	 * @Title: base64ToFile @Description: base64转文件 @param: @param
	 * base64 @param: @param filePath 包含文件名的全路径 @param: @return @return:
	 * String @throws
	 */
	public static String base64ToFile(String base64, String filePath) {

		if (base64 == null && filePath == null) {
			return "生成文件失败，请给出相应的数据。";
		}
		try {
			// StandardOpenOption枚举类说明：
			// READ 以读取方式打开文件
			// WRITE 已写入方式打开文件
			// CREATE 如果文件不存在，创建
			// CREATE_NEW 如果文件不存在，创建；若存在，异常。
			// APPEND 在文件的尾部追加
			// DELETE_ON_CLOSE 当流关闭的时候删除文件
			// TRUNCATE_EXISTING 把文件设置为0字节
			// SPARSE 文件不够时创建新的文件
			// SYNC 同步文件的内容和元数据信息随着底层存储设备
			// DSYNC 同步文件的内容随着底层存储设备

			byte[] decode = Base64.getMimeDecoder().decode(base64);

			for (int i = 0; i < decode.length; ++i) {
				if (decode[i] < 0) {// 调整异常数据
					decode[i] += 256;
				}
			}
			Files.write(Paths.get(filePath), Base64.getMimeDecoder().decode(base64), StandardOpenOption.CREATE);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "指定路径下生成文件成功！";

	}

	public static String base64ToFile2(String base64, String filePath) {

		if (base64 == null && filePath == null) {
			return "生成文件失败，请给出相应的数据。";
		}
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
}
