package com.example.demo.utils;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public class FileUtil {
	
	// 这里可以提供更多地编码格式
	public static final String[] CHARSET = new String[] { "GBK", "GB2312", "ISO-8859-1", "ISO-8859-2","UTF-8" };
	
    /**
     * 第一步：判断文件是否为空   true：返回提示为空信息   false：执行第二步
     * 第二步：判断目录是否存在   不存在：创建目录
     * 第三部：通过输出流将文件写入硬盘文件夹并关闭流
     * @param file
     * @return
     */
    public static String uploadFile(MultipartFile file){
        String fileName = file.getOriginalFilename();
        String filePath = "F:/nfs/upload/";
        File targetFile = new File(filePath);
        //第一步：判断文件是否为空
        if(file.isEmpty()) {
        	return fileName+"文件为空";
        }
        //第二步：判断目录是否存在   不存在：创建目录
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        //第三部：通过输出流将文件写入硬盘文件夹并关闭流
        BufferedOutputStream stream = null;
        try {
            stream = new BufferedOutputStream(new FileOutputStream(filePath+fileName));
            stream.write(file.getBytes());
            stream.flush();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                if (stream != null) stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fileName+"上传成功";
    }
    
    
    /**   
     * @Title: changeFileEncode   
     * @Description: 修改文件编码格式
     * @param: originalFileName
     * @param: newFileName
     * @param: newCharset 字符格式
     * @return: String      
     * @throws   
     */  
    public static String changeFileEncode(String originalFileName,String newFileName,String newCharset) {
    	
    	// 将上gbk格式文件转换为utf8
    	BufferedReader bufferedReader = null;
    	BufferedWriter bufferedWriter =null;
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(originalFileName)));
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFileName), newCharset));
			String string = "";
			while ((string = bufferedReader.readLine()) != null) {
				bufferedWriter.write(string);
				bufferedWriter.newLine();
				bufferedWriter.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferedReader != null) {
					bufferedReader.close();
				}
				if (bufferedWriter != null) {
					bufferedWriter.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

    	return newFileName;
    }
    
    
    /**
     * @throws IOException    
     * @Title: mergeFileListToOneFile   
     * @Description: // 合并多个文件内容到一个文件中
     * @param: fileList
     * @param: newFileName 文件的完整名：/home/admin/file/mergeFile.txt
     * @return: Map<String,List<String>> 返回合并的文件列表：文件、目录列表      
     * @throws   
     */  
    public static Map<String,List<String>> mergeFileListToOneFile(List<File> fileList,String newFileName) {
    	
    	BufferedWriter bw = null;
    	Map<String,List<String>> fileAndFloderMap = UtilMisc.toMap("file", new ArrayList<>(), "floder", new ArrayList<>());
		try {
			bw = new BufferedWriter(new FileWriter(newFileName));
			
			for(File file : fileList){
				if(file.isFile()) {
					fileAndFloderMap.get("file").add(file.getPath());
					BufferedReader br = null;
					try {
						br = new BufferedReader(new FileReader(file));
						String line;
						while((line=br.readLine())!=null) {
							bw.write(line);
							bw.newLine();
						}
						bw.flush();
					} catch (IOException e) {
						e.printStackTrace();
					} finally {
						if (br != null) {
							try {
								br.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				} else {
					fileAndFloderMap.get("floder").add(file.getPath());
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
    	 
		System.out.println("输入目录下文件个数为"+fileAndFloderMap.get("file").size());
		System.out.println("输入目录下文件夹个数为"+fileAndFloderMap.get("floder").size());
		
		return fileAndFloderMap;
    }
    
    public static Map<String,List<String>> mergeFileListToOneFile(List<File> fileList,String newFileName,String newCharset) {
    	BufferedWriter bw = null;
    	Map<String,List<String>> fileAndFloderMap = UtilMisc.toMap("file", new ArrayList<>(), "floder", new ArrayList<>());
		try {
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFileName),newCharset));
			
			for(File file : fileList){
				if(file.isFile()) {
					fileAndFloderMap.get("file").add(file.getPath());
					BufferedReader br = null;
					try {
						br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
						String line;
						while((line=br.readLine())!=null) {
							bw.write(line);
							bw.newLine();
						}
						bw.flush();
					} catch (IOException e) {
						e.printStackTrace();
					} finally {
						if (br != null) {
							try {
								br.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				} else {
					fileAndFloderMap.get("floder").add(file.getPath());
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
    	 
		System.out.println("输入目录下文件个数为"+fileAndFloderMap.get("file").size());
		System.out.println("输入目录下文件夹个数为"+fileAndFloderMap.get("floder").size());
		
		return fileAndFloderMap;
    	
    }
    
    
    
    
}