package com.example.demo.controller.fileupanddown;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.example.demo.config.FileProperties;
import com.example.demo.utils.FileUtil;

@RestController
public class FileUploadController {
	
	private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);
	
	// 上传文件路径
	@Autowired
	FileProperties fileProperties;

    /**
     * 实现文件上传
     * */
    @RequestMapping(value = "fileUpload",method=RequestMethod.POST)
    public String fileUpload(@RequestParam("fileName") MultipartFile file){
        if(file.isEmpty()){
        	return "文件为空";
        }

        // 获取文件名
        String fileName = file.getOriginalFilename();
        logger.info("上传的文件名为：" + fileName);

        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        logger.info("上传的后缀名为：" + suffixName);
        
        // 获取上传文件路径
        String uploadPath = fileProperties.getUploadPath();
        
        
        // 解决中文问题，liunx下中文路径，图片显示问题
//        fileName = UUID.randomUUID() + suffixName;
        
        File dest = new File(uploadPath + fileName);

        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        
        try {
            file.transferTo(dest); //保存文件
            return "上传成功";
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return "上传失败";
    }

    
    /**
     * 实现多文件上传
     * */
    /**public @ResponseBody String multifileUpload(@RequestParam("fileName")List<MultipartFile> files) */
    @RequestMapping(value="multifileUpload",method=RequestMethod.POST) 
    public @ResponseBody String multifileUpload(HttpServletRequest request){
        
        List<MultipartFile> files = ((MultipartHttpServletRequest)request).getFiles("fileName");
        
        if(files.isEmpty()){
            return "文件为空";
        }

        // 获取上传文件路径
        String uploadPath = fileProperties.getUploadPath();
        
        for(MultipartFile file:files){
            String fileName = file.getOriginalFilename();
            int size = (int) file.getSize();
            System.out.println(fileName + "-->" + size);
            
            if(file.isEmpty()){
                return "false";
            }else{        
                File dest = new File(uploadPath + fileName);
                if(!dest.exists()){ //判断文件父目录是否存在
                    dest.mkdirs();
                }
                try {
                    file.transferTo(dest);
                }catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    return "false";
                } 
            }
        }
        return "true";
    }
    
    //处理多文件上传
    @RequestMapping(value="/testuploadFiles", method = RequestMethod.POST)
    public String multipleFilesUpload(HttpServletRequest request){
        //获取上传的文件数组
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("fileName");
        
        if(files.isEmpty()){
            return "false";
        }
        
        // 获取上传文件路径
        String uploadPath = fileProperties.getUploadPath();
        
        File dir = new File(uploadPath);
        if (!dir.isDirectory()) {//文件目录不存在，就创建一个
            dir.mkdirs();
        }
//       
            // 遍历文件数组，一个个上传
           for (MultipartFile uploadFile : files) {
                // 获取当前上传文件名
                String fileName = uploadFile.getOriginalFilename();
                
                //服务端保存的文件对象
                File file = new File(dir, fileName);
                
                System.out.println("file文件真实路径:" + file.getAbsolutePath());
                
                try {
	                // 实现上传
	                uploadFile.transferTo(file);
	                System.out.println(fileName+"上传成功");
	            } catch (IOException e) {
	                e.printStackTrace();
	                return fileName +"上传失败";
	            }
           }
          
       return "所有文件上传成功";
    }
    
  //处理多文件上传
    @RequestMapping(value="/testuploadFiles2", method = RequestMethod.POST)
    public String multipleFilesUpload2(HttpServletRequest request){
        //获取上传的文件数组
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("fileName");
        //遍历处理文件
        String info = "";
        for (MultipartFile file:files) {
            try {
                String s = FileUtil.uploadFile(file);
                info = info+"-"+s;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return info;
    }
}