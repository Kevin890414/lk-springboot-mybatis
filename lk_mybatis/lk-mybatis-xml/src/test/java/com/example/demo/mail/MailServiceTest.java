package com.example.demo.mail;
/*package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.example.demo.service.MailService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceTest {

    @Autowired
    private MailService mailService;

    @Autowired
    private TemplateEngine templateEngine;

    @Test
    public void testSimpleMail() throws Exception {
//        mailService.sendSimpleMail("likui_19890414@163.com","这是一封简单邮件","大家好，这是我的第一封邮件！");

		String content = "<h3><a href='http://www.baidu.com'>百度一下，你就知道</a></h3>";
        mailService.sendSimpleMail("likui_19890414@163.com","这是一封简单邮件发送html邮件",content);
    }

    @Test
    public void testHtmlMail() throws Exception {
        String content="<html>\n" +
                "<body>\n" +
                "    <h3>hello world ! 这是一封html邮件!</h3>\n" +
                "</body>\n" +
                "</html>";
        mailService.sendHtmlMail("likui_19890414@163.com","这是一封HTML邮件",content);
    }

    @Test
    public void sendAttachmentsMail() {
        String filePath="C:\\bqs\\分期还打包目录\\test\\spring-boot-package-war.war";
        mailService.sendAttachmentsMail("likui_19890414@163.com", "主题：带附件的邮件", "有附件，请查收！", filePath);
    }


    @Test
    public void sendInlineResourceMail() {
        String rscId = "neo006";
        String content="<html><body>这是有图片的邮件：<img src=\'cid:" + rscId + "\' ></body></html>";
        String imgPath = "C:\\bqs\\分期还打包目录\\test\\login-bg.jpg";

        mailService.sendInlineResourceMail("likui_19890414@163.com", "主题：这是有图片的邮件", content, imgPath, rscId);
    }


    *//**
     * 按照模板发送邮件
     *//*
    @Test
    public void sendTemplateMail() {
    	 通常我们使用邮件发送服务的时候，都会有一些固定的场景，比如重置密码、注册确认等，
        	给每个用户发送的内容可能只有小部分是变化的。所以，很多时候我们会使用模板引擎来
        	为各类邮件设置成模板，这样我们只需要在发送时去替换变化部分的参数即可。
        	这个时候我们就需要使用MimeMessage来设置复杂一些的邮件内容 
        //创建邮件正文
        Context context = new Context();
        context.setVariable("id", "006");
        String emailContent = templateEngine.process("template1", context);

        mailService.sendHtmlMail("likui_19890414@163.com","主题：这是模板邮件",emailContent);
    }
}
*/