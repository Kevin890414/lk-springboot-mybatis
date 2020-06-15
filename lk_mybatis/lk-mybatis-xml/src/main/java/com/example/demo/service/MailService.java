package com.example.demo.service;
public interface MailService {

    public void sendSimpleMail(String to, String subject, String content);//简单邮件

    public void sendHtmlMail(String to, String subject, String content);//html邮件

    public void sendAttachmentsMail(String to, String subject, String content, String filePath);//带附件邮件

    public void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId);//带静态资源文件（图片）的邮件

}
