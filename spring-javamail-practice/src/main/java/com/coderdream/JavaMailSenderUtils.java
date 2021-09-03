package com.coderdream;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.util.CollectionUtils;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author ：CoderDream
 * @date ：Created in 2021/9/3 16:37
 * @description：
 * @modified By：CoderDream
 * @version: $
 */
public class JavaMailSenderUtils {

    /**
     * 测试发送邮件 实际中可以在service中使用（若使用的spring配置的方式创建JavaMailSender，则需注入使用）
     * 此处为方便使用main方法
     * @param args
     */
    public static void main(String[] args) {
        JavaMailSenderInfo javaMailSenderInfo = new JavaMailSenderInfo();
        javaMailSenderInfo.setToAddress("xulin.wh@qq.com");
        javaMailSenderInfo.setSubject("邮件主题");
        javaMailSenderInfo.setContent("邮件内容");
        javaMailSenderInfo.setHtml(true);
        List<String> list = new ArrayList<>();
   //     list.add("file:///D:/DailyReport/202108.jpg");
        list.add("D:\\DailyReport\\202108.jpg");
        // list.add("图片2");
        // list.add("图片3");
        // list.add("图片4");
        javaMailSenderInfo.setPhotoList(list);
        javaMailSenderInfo.setAttachList(list);
        // JavaMailSenderUtils.sendSimpleMail(javaMailSenderInfo);
        JavaMailSenderUtils.sendPhotoMail(javaMailSenderInfo);
        // JavaMailSenderUtils.sendAttacheMail(javaMailSenderInfo);
        // JavaMailSenderUtils.sendPhotoAndAttacheMail(javaMailSenderInfo);

    }

    private static final JavaMailSender javaMailSender = InitJavaMailSender.getJavaMailSender();

    private static final String htmlStartStr = "<html><body>";

    private static final String htmlEndStr = "</body></html>";

    /**
     * 发送简单邮件 文本 html
     * @param javaMailSenderInfo
     * @return
     */
    public static boolean sendSimpleMail(JavaMailSenderInfo javaMailSenderInfo) {
        // 创建邮件消息 简单邮件可直接使用SimpleMailMessage
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = null;
        try {
            // 创建消息辅助器 参数二 为true时为多组件 html 等
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
            // 设置公共字段
            setMailCommonField(mimeMessageHelper, javaMailSenderInfo);
            // 文本内容 可指定是否是html
            mimeMessageHelper.setText(javaMailSenderInfo.getContent(), javaMailSenderInfo.isHtml());
            javaMailSender.send(mimeMessage);
            System.out.println("简单邮件发送成功");
            return true;
        } catch (MessagingException | javax.mail.MessagingException e) {
            e.printStackTrace();
            System.out.println("简单邮件发送失败");
            return false;
        }
    }

    /**
     * 发送带图片的邮件
     * @param javaMailSenderInfo
     * @return
     */
    public static boolean sendPhotoMail(JavaMailSenderInfo javaMailSenderInfo) {
        // 创建邮件消息 简单邮件可直接使用SimpleMailMessage
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = null;
        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
            setMailCommonField(mimeMessageHelper, javaMailSenderInfo);
            List<String> photoList = javaMailSenderInfo.getPhotoList();
            // html内容
            StringBuilder text = new StringBuilder(htmlStartStr);
            text.append(javaMailSenderInfo.getContent());
            if (!CollectionUtils.isEmpty(photoList)) {
                for (String photo : photoList) {
                    // 每个图片创建一个标题吧 每个图片关联一个cid
                    String cid = UUID.randomUUID().toString();
                    text.append("<br/><h1>"+ javaMailSenderInfo.getSubject() +"</h1><br/><img src='cid:"+ cid +"'/>");
                    File imageFile = new File(photo);

                    mimeMessageHelper.addInline(cid,imageFile );
                }
            }
            // 为什么会有最后一个图片图裂不显示的问题（求大佬指点）
            // String cid = UUID.randomUUID().toString();
            // mimeMessageHelper.addInline(cid, new File(""));
            text.append(htmlEndStr);
            mimeMessageHelper.setText(text.toString(),true);
            javaMailSender.send(mimeMessage);
            System.out.println("带图片的邮件发送成功");
            return true;
        } catch (MessagingException | javax.mail.MessagingException e) {
            e.printStackTrace();
            System.out.println("带图片的邮件发送失败");
            return false;
        }
    }

    /**
     * 发送带附件的邮件
     * @param javaMailSenderInfo
     * @return
     */
    public static boolean sendAttacheMail(JavaMailSenderInfo javaMailSenderInfo) {
        // 创建邮件消息 简单邮件可直接使用SimpleMailMessage
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = null;
        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
            setMailCommonField(mimeMessageHelper, javaMailSenderInfo);
            List<String> attachList = javaMailSenderInfo.getAttachList();
            // 邮件文本
            mimeMessageHelper.setText(javaMailSenderInfo.getContent(), javaMailSenderInfo.isHtml());
            if (!CollectionUtils.isEmpty(attachList)) {
                for (String attach : attachList) {
                    // 添加附件（最后一个附件是图片也存在图裂的问题，但可下载使用，求大佬指点）
                    FileSystemResource fileSystemResource = new FileSystemResource(attach);
                    // 参数一为附件名称
                    mimeMessageHelper.addAttachment(fileSystemResource.getFilename(), fileSystemResource);
                }
            }
            javaMailSender.send(mimeMessage);
            System.out.println("带附件的邮件发送成功");
            return true;
        } catch (MessagingException | javax.mail.MessagingException e) {
            e.printStackTrace();
            System.out.println("带附件的邮件发送失败");
            return false;
        }
    }

    /**
     * 发送带图片和附件的邮件
     * @param javaMailSenderInfo
     * @return
     */
    public static boolean sendPhotoAndAttacheMail(JavaMailSenderInfo javaMailSenderInfo) {
        // 创建邮件消息 简单邮件可直接使用SimpleMailMessage
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = null;
        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
            setMailCommonField(mimeMessageHelper, javaMailSenderInfo);
            List<String> photoList = javaMailSenderInfo.getPhotoList();
            // 放入图片和内容
            StringBuilder text = new StringBuilder(htmlStartStr + javaMailSenderInfo.getContent());
            if (!CollectionUtils.isEmpty(photoList)) {
                for (String photo : photoList) {
                    String cid = UUID.randomUUID().toString();
                    text.append("<br/><img src='cid:"+ cid +"'>");
                    mimeMessageHelper.addInline(cid, new File(photo));
                }
            }
            text.append(htmlEndStr);
            mimeMessageHelper.setText(text.toString(),true);
            // 放入附件
            List<String> attachList = javaMailSenderInfo.getAttachList();
            if (!CollectionUtils.isEmpty(attachList)) {
                for (String attach : attachList) {
                    // 添加附件
                    FileSystemResource fileSystemResource = new FileSystemResource(attach);
                    // 参数一为附件名称
                    mimeMessageHelper.addAttachment(fileSystemResource.getFilename(), fileSystemResource);
                }
            }
            javaMailSender.send(mimeMessage);
            System.out.println("带图片和附件的邮件发送成功");
            return true;
        } catch (MessagingException | javax.mail.MessagingException e) {
            e.printStackTrace();
            System.out.println("带图片和附件邮件发送失败");
            return false;
        }
    }

    /**
     * 发送由freemarker模板技术生成的邮件
     * @param javaMailSenderInfo
     * @return
     */
    public static boolean sendFreemarkerMail(JavaMailSenderInfo javaMailSenderInfo) {
        // 创建邮件消息 简单邮件可直接使用SimpleMailMessage
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = null;
        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
            setMailCommonField(mimeMessageHelper, javaMailSenderInfo);
            /**
             * 此处没有实现采用模板技术（可自行探索）
             * 封装模板所需要的数据
             * 读取freemarker的模板文件 xxx.ftl 进行静态化，生成html形式的字符串
             * 将生成好的html字符串设置到邮件的正文中
             */
            javaMailSender.send(mimeMessage);
            System.out.println("freemarker模板技术生成的邮件发送成功");
            return true;
        } catch (MessagingException | javax.mail.MessagingException e) {
            e.printStackTrace();
            System.out.println("freemarker模板技术生成的邮件发送失败");
            return false;
        }
    }

    /**
     * 设置邮件的公共字段
     * @param mimeMessageHelper
     * @param javaMailSenderInfo
     * @throws MessagingException
     */
    private static void setMailCommonField(MimeMessageHelper mimeMessageHelper, JavaMailSenderInfo javaMailSenderInfo) throws MessagingException, javax.mail.MessagingException {
        // 发件人
        mimeMessageHelper.setFrom(InitJavaMailSender.username);
        // 收件人 多人用数组
        mimeMessageHelper.setTo(javaMailSenderInfo.getToAddress());
        // 主题
        mimeMessageHelper.setSubject(javaMailSenderInfo.getSubject());
        // 日期
        mimeMessageHelper.setSentDate(new Date());
    }

}
