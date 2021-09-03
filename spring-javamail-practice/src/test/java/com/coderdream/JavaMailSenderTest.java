package com.coderdream;

/**
 * @author ：CoderDream
 * @date ：Created in 2021/9/3 17:22
 * @description：
 * @modified By：CoderDream
 * @version: $
 */
import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimeUtility;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;
import java.io.File;

public class JavaMailSenderTest {

    JavaMailSender javaMailSender = InitJavaMailSender.getJavaMailSender();

    @Test
    public void testSimpleTextMail(){   //发送普通文本邮件

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("acoder@126.com");// 发送者，可选的
        mailMessage.setTo("xulin.wh@qq.com");//接受者
        mailMessage.setSubject("测试邮件");//主题
        mailMessage.setText("Test Email send by javaMailSender!");//邮件内容

        javaMailSender.send(mailMessage);
    }

    @Test
    public void testMimeMail() throws Exception {   //发送HTML格式的邮件

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom("acoder@126.com");
        helper.setTo("xulin.wh@qq.com");
        helper.setSubject("主题：嵌入静态资源");
        helper.setText("<html><body><a href=\"http://www.baidu.com\" >百度</a></body></html>", true);

        javaMailSender.send(mimeMessage);
    }

    @Test
    public void testMimeInlineMail() throws Exception { //发送HTML格式含图片的邮件

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom("acoder@126.com");
        helper.setTo("xulin.wh@qq.com");
        helper.setSubject("主题：嵌入静态资源");
        helper.setText("<html><body><img src=\"cid:logo\" ></body></html>", true);
        FileSystemResource logoFile = new FileSystemResource(new File("D:\\DailyReport\\202108.jpg"));
        helper.addInline("logo", logoFile);

        javaMailSender.send(mimeMessage);
    }

    @Test
    public void testAttachmentMail() throws Exception { //发送含附件的邮件

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);// 第二个参数设置为true，表示允许添加附件
        helper.setFrom("acoder@126.com");
        helper.setTo("xulin.wh@qq.com");
        helper.setSubject("发送含图片附件的邮件");
        helper.setText("含有附件的邮件");

        helper.addAttachment(MimeUtility.encodeText("附件-1.jpg"), new File("E:/attachment1.jpg"));
        helper.addAttachment(MimeUtility.encodeText("附件-2.jpg"), new File("E:/attachment2.jpg"));

        javaMailSender.send(mimeMessage);
    }
}

