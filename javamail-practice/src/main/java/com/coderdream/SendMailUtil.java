package com.coderdream;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @author ：CoderDream
 * @date ：Created in 2021/9/2 15:35
 * @description：邮件工具类
 * @modified By：CoderDream
 * @version: 1.0$
 */
public class SendMailUtil {

    public static void sendEmail(String to, String content) throws Exception {
        //1.创建连接对象
        Properties properties = new Properties();
        //1.2 设置发送邮件的服务器
        properties.put("mail.smtp.host","smtp.126.com");
        //1.3 需要经过授权,用户名和密码的校验(必须)
        properties.put("mail.smtp.auth",true);
        //1.4 默认以25端口发送
        properties.setProperty("mail.smtp.socketFactory.class" , "javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.smtp.socketFactory.fallback" , "false");
        properties.setProperty("mail.smtp.port" , "465");
        properties.setProperty("mail.smtp.socketFactory.port" , "465");
        //1.5 认证信息
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("acoder@126.com" , "授权码");
            }
        });
        //2.创建邮件对象
        Message message = new MimeMessage(session);
        //2.1设置发件人
        message.setFrom(new InternetAddress("acoder@126.com"));
        //2.2设置收件人
        message.setRecipient(Message.RecipientType.TO,new InternetAddress(to));
        //2.3设置抄送者(PS:没有这一条网易会认为这是一条垃圾短信，而发不出去)
        message.setRecipient(Message.RecipientType.CC,new InternetAddress("acoder@126.com"));
        //2.4设置邮件的主题
        message.setSubject("主题");
        //2.5设置邮件的内容
        message.setContent(""+content+"","text/html;charset=utf-8");
        //3.发送邮件
        Transport.send(message);
    }

    //直接在main方法下测试
    public static void main(String[] args) throws Exception {
        sendEmail("xulin.wh@qq.com", "邮件内容");
    }
}
