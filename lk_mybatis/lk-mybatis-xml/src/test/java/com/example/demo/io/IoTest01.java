package com.example.demo.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * 类说明
 * 
 * @author likui
 * @since 2020年6月16日 上午9:29:35
 * @version 1.0
 *
 */
public class IoTest01 {

	/**   
	 * @Title: getFileContent   
	 * @Description: 使用字节流读取本地文件
	 * @param: @param file
	 * @param: @throws IOException      
	 * @return: void      
	 * @throws   
	 */  
	public static void getFileContent(File file) throws IOException {
		// 创建文件缓冲输入流
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
		byte[] buf = new byte[1024];// 创建字节数组，存储临时读取的数据
		int len = 0;// 记录数据读取的长度
		// 循环读取数据
		while ((len = bis.read(buf)) != -1) { // 长度为-1则读取完毕
			System.out.println(new String(buf, 0, len));
		}
		bis.close(); // 关闭流
	}
	public static void getFileContent1(File file) throws IOException {
		// 创建文件缓冲输入流
		FileInputStream bis = new FileInputStream(file);
		byte[] buf = new byte[1024];// 创建字节数组，存储临时读取的数据
		int len = 0;// 记录数据读取的长度
		// 循环读取数据
		while ((len = bis.read(buf)) != -1) { // 长度为-1则读取完毕
			System.out.println(new String(buf, 0, len));
		}
		bis.close(); // 关闭流
	}

	/**
	 * @Title: getFileContent @Description: 使用字符处理流读取本地文件内容 @param: @param
	 *         path @param: @throws IOException @return: void @throws
	 */
	public static void getFileContent(String path) throws IOException {
		File f = new File(path);
		if (f.exists()) { // 判断文件或目录是否存在
			if (f.isFile()) {
				BufferedReader br = new BufferedReader(new FileReader(path));// 该缓冲流有一个readLine()独有方法
				String s = null;
				while ((s = br.readLine()) != null) {// readLine()每次读取一行
					System.out.println(s);
				}
				br.close();// 关闭流
			}
		}
	}
	
	/**   
	 * @Title: writeFile   
	 * @Description: 使用字符流写入数据到指定文件
	 * @param: @param outFilePath
	 * @param: @throws IOException      
	 * @return: void      
	 * @throws   
	 */  
	public static void writeFile(String outFilePath) throws IOException {
        //以标准输入作为扫描来源
        Scanner sc = new Scanner(System.in);
        File f = new File(outFilePath);
        BufferedWriter bw = new BufferedWriter(new FileWriter(f));
        if(!f.exists()) {
            f.createNewFile();
        }
        while(true) {
            String s = sc.nextLine();
            bw.write(s);
            bw.flush();
            if(s.equals("结束") || s.equals("")) {
                System.out.println("写入数据结束！");
                sc.close();
                bw.close();
                return;
            }
        }
    }
	
	/**   
	 * @Title: testConvert   
	 * @Description: 使用转换流（InputStreamReader/OutputStreamWriter），对写入数据进行改进 
	 * @param: @param f
	 * @param: @throws IOException      
	 * @return: void      
	 * @throws   
	 */  
	public static void testConvert(File f) throws IOException {
        if(!f.exists()) {
            f.createNewFile();
        }
        //以System.in作为读取的数据源，即从键盘读取
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new FileWriter(f,true)); //允许添加内容，不会清除原有数据源
        String s = null;
        while(!(s = br.readLine()).equals("")) {
            bw.write(s);
            bw.newLine();//空一行
        }
        bw.flush();        
        bw.close();
        br.close();
    }
	
	//字节流实现文件拷贝
    public static String copyFile(String src, String dest) throws IOException, ClassNotFoundException {
        File srcFile = new File(src);//源文件数据源
        File desFile = new File(dest);//写入到目标数据源
        //数据源不存在
        if(!srcFile.exists() || !desFile.exists()) {
            throw new ClassNotFoundException("源文件或者拷贝目标文件地址不存在！");
        }
        //非文件类型
        if(!srcFile.isFile() || !desFile.isFile()) {
            return "源文件或者目标文件不是文件类型!";
        }
        InputStream is = null;
        OutputStream os = null;
        byte[] buf = new byte[1024];//缓存区
        int len = 0;//读取长度
        try {
            is = new BufferedInputStream(new FileInputStream(srcFile));//读取数据源
            os = new BufferedOutputStream(new FileOutputStream(desFile));//写入到数据源            
            while((len = is.read(buf)) != -1) { //读取长度不为-1，继续读取
                os.write(buf, 0, len);; //读取内容之后马上写入目标数据源
            }
            os.flush();//输出
            
            return "文件拷贝成功！查看拷贝文件路径：" + desFile.getPath();                        
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(is != null)
                is.close();
            if(os != null)
                os.close();
        }
        return "文件拷贝失败";
    }

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		String filePath = "F:\\总资料\\虚拟机软件安装情况.txt";
		String outFilePath = "F:\\总资料\\虚拟机软件安装情况1.txt";
//		getFileContent(filePath);
//		getFileContent1(new File(filePath));
//		writeFile(outFilePath);
//		testConvert(new File(outFilePath));
//		copyFile(filePath, outFilePath);
		
		IoTest01 test = new IoTest01();
		System.out.println("对象地址："+test);
		test.test();
		IoTest01.test2();
		


	}
	
	public void test(){

		System.out.println(this);

	}
	public static void test2(){
		
		System.out.println(IoTest01.class);
		
	}
	
	
	
}
