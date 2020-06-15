package com.example.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Lenovo
 * 文件相关的配置类
 */
@ConfigurationProperties(prefix="file")
public class FileProperties {
  private String uploadPath;
 
  public String getUploadPath() {
    return uploadPath;
  }
  public void setUploadPath(String uploadPath) {
    this.uploadPath = uploadPath;
  }
}