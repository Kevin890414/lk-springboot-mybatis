package com.example.demo.controller.fileupanddown;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DownLoadFile {
	
	@RequestMapping( value = "/download1", method = RequestMethod.GET )
	public void testDownload( HttpServletResponse res ) {
	    String fileName = "E:\\excel\\用户信息20190830.xls";
	    
//	    res.setHeader("content-type", "application/octet-stream");
	    res.setHeader("content-type", "application/octet-stream;charset=UTF-8");
	    res.setContentType("application/octet-stream");
	    res.setHeader("Content-Disposition", "attachment; filename=" + fileName);
	    byte[] buff = new byte[1024];
	    BufferedInputStream bis = null;
	    OutputStream os = null;
	 
	    try {
	      os = res.getOutputStream();
	      bis = new BufferedInputStream(new FileInputStream( 
	                new File(fileName )));
	      int i = bis.read(buff);
	 
	      while (i != -1) {
	        os.write(buff, 0, buff.length);
	        os.flush();
	        i = bis.read(buff);
	      }
	    } catch ( IOException e ) {
	      e.printStackTrace();
	    } finally {
	      if (bis != null) {
	        try {
	          bis.close();
	        } catch (IOException e) {
	          e.printStackTrace();
	        }
	      }
	    }
	    System.out.println("export file finish");
	  }
	
	//文件下载相关代码
	@RequestMapping(value = "/download2", method = RequestMethod.POST )
	public String downloadFile(HttpServletRequest request, HttpServletResponse response) {
	    String fileName = "E:\\excel\\用户信息20190830.xls";// 设置文件名，根据业务需要替换成要下载的文件名
	    if (fileName != null) {
	        //设置文件路径
	        File file = new File(fileName);
	        if (file.exists()) {
//	            response.setContentType("application/force-download");// 设置强制下载不打开
	            response.setHeader("content-type", "application/octet-stream;charset=UTF-8");
//	            response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
	            byte[] buffer = new byte[1024];
	            FileInputStream fis = null;
	            BufferedInputStream bis = null;
	            try {
	                fis = new FileInputStream(file);
	                bis = new BufferedInputStream(fis);
	                OutputStream os = response.getOutputStream();
	                int i = bis.read(buffer);
	                while (i != -1) {
	                    os.write(buffer, 0, i);
	                    i = bis.read(buffer);
	                }
	                System.out.println("success");
	            } catch (Exception e) {
	                e.printStackTrace();
	            } finally {
	                if (bis != null) {
	                    try {
	                        bis.close();
	                    } catch (IOException e) {
	                        e.printStackTrace();
	                    }
	                }
	                if (fis != null) {
	                    try {
	                        fis.close();
	                    } catch (IOException e) {
	                        e.printStackTrace();
	                    }
	                }
	            }
	        }
	    }
	    return null;
	
	}
	
	//文件下载相关代码
	@RequestMapping(value = "/download3", method = RequestMethod.GET )
	public Object callFileDownServiceByJson(HttpServletRequest request, HttpServletResponse response) {
		  OutputStream out = null;
		  FileInputStream in = null;
		  String fileName = "用户信息20190830.xls";
		  try {
//		   String jsonString = HttpJsonUtils.getInputJsonString(request);
//		   JSONObject reqJson = JSONObject.parseObject(jsonString);
		   String servicePath = "E:\\excel\\用户信息20190830.xls";
//		   //获取文件编号给servicePath
//		   if (reqJson.containsKey(TellerConstants.HEADER_TranCode)) {
//		    servicePath = reqJson.getString(TellerConstants.HEADER_TranCode);
//		   } else {
//		    String paramPath = PropertyUtil.getProperty("requestMessage.servicePath");
//		    servicePath = (String) JSONMapUtil.getPathData(paramPath, reqJson);
//		   }
//
//		   FileInfo fileInfo = config.getFileInfo(servicePath);
//		   if(null == fileInfo) {
//		    return ReturnUtil.printBackInfo(log, "callFileDownServiceByJson - 下载CIP本地文件编号" + servicePath + "不存在.");
//		   }
//		   fileName = fileInfo.getOriginalFileName();
//		   //此时servicePath为保存文件的全路径
//		   servicePath = fileInfo.getResouceInfo();
//		   log.info("callFileDownServiceByJson - 下载CIP本地文件路径为: " + servicePath + ",文件名为:" + fileName);
		   response.reset();
		   response.setHeader("Content-Type", "application/octet-stream;charset=UTF-8");
		   response.setHeader("Content-disposition",
		     "attachment; filename=" + java.net.URLEncoder.encode(fileName, "UTF-8"));

		   File my_file = new File(servicePath);
		   if (!my_file.exists()) {
		    return "";
		   }
		   out = response.getOutputStream();
		   in = new FileInputStream(my_file);
		   byte[] buffer = new byte[1024];
		   int length;
		   while ((length = in.read(buffer)) > 0) {
		    out.write(buffer, 0, length);
		   }

		   return null;
		  } catch (Exception e) {
		   return "";
		  } finally {
		   try {
		    if (null != in) {
		     in.close();
		    }
		   } catch (IOException e) {
		    e.printStackTrace();
		   }
		   if (out != null) {
               try {
            	   out.close();
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
		  }
	}
	
	//文件下载相关代码
	@RequestMapping(value = "/download4", method = RequestMethod.POST )
	public Object callFileDownServiceByJson2(HttpServletRequest request, HttpServletResponse response) {
		OutputStream out = null;
		FileInputStream in = null;
		String fileName = "用户信息20190830.xls";
		try {
//		   String jsonString = HttpJsonUtils.getInputJsonString(request);
//		   JSONObject reqJson = JSONObject.parseObject(jsonString);
			String servicePath = "E:\\excel\\用户信息20190830.xls";
//		   //获取文件编号给servicePath
//		   if (reqJson.containsKey(TellerConstants.HEADER_TranCode)) {
//		    servicePath = reqJson.getString(TellerConstants.HEADER_TranCode);
//		   } else {
//		    String paramPath = PropertyUtil.getProperty("requestMessage.servicePath");
//		    servicePath = (String) JSONMapUtil.getPathData(paramPath, reqJson);
//		   }
//
//		   FileInfo fileInfo = config.getFileInfo(servicePath);
//		   if(null == fileInfo) {
//		    return ReturnUtil.printBackInfo(log, "callFileDownServiceByJson - 下载CIP本地文件编号" + servicePath + "不存在.");
//		   }
//		   fileName = fileInfo.getOriginalFileName();
//		   //此时servicePath为保存文件的全路径
//		   servicePath = fileInfo.getResouceInfo();
//		   log.info("callFileDownServiceByJson - 下载CIP本地文件路径为: " + servicePath + ",文件名为:" + fileName);
			response.reset();
			response.setHeader("Content-Type", "application/octet-stream;charset=UTF-8");
			response.setHeader("Content-disposition",
					"attachment; filename=" + java.net.URLEncoder.encode(fileName, "UTF-8"));
			
			File my_file = new File(servicePath);
			if (!my_file.exists()) {
				return "";
			}
			out = response.getOutputStream();
			in = new FileInputStream(my_file);
			byte[] buffer = new byte[1024];
			int length;
			while ((length = in.read(buffer)) > 0) {
				out.write(buffer, 0, length);
			}
			
			return null;
		} catch (Exception e) {
			return "";
		} finally {
			try {
				if (null != in) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
  
}
