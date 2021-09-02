package com.coderdream;

import freemarker.template.TemplateException;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Map;

/**
 * 邮件发送工具类
 * 
 * @date 2014年4月26日 下午3:30:04
 * @author 曹志飞
 * @Description:	
 * @project mailUtil
 */
public class MailUtil {

	/**
	 * 根据模板名称查找模板，加载模板内容后发送邮件
	 * 
	 * @Date:2014年4月26日 上午11:02:58
	 * @author 曹志飞
	 * @param receiver
	 *            收件人地址
	 * @param subject
	 *            邮件主题
	 * @param map
	 *            邮件内容与模板内容转换对象
	 * @param templateName
	 *            模板文件名称
	 * @throws IOException
	 * @throws TemplateException
	 * @throws MessagingException
	 * @Description:
	 * @return void
	 */
	public static void sendMailByTemplate(String receiver, String subject,
			Map<String, String> map, String templateName) throws IOException,
			TemplateException, MessagingException {
		String maiBody = "";
		String server = ConfigLoader.getServer();
		String sender = ConfigLoader.getSender();
		String username = ConfigLoader.getUsername();
		String password = ConfigLoader.getPassword();
		String nickname = ConfigLoader.getNickname();
		MailSender mail = new MailSender(server);
		mail.setNeedAuth(true);
		mail.setNamePass(username, password, nickname);
		maiBody = TemplateFactory.generateHtmlFromFtl(templateName, map);
		mail.setSubject(subject);
		mail.setBody(maiBody);
		mail.setReceiver(receiver);
		mail.setSender(sender);
		mail.sendout();
	}
	
	public static void sendMailByTemplateWithMapObject(String receiver, String subject,
			Map<String, Object> map, String templateName) throws IOException,
			TemplateException, MessagingException {
		String maiBody = "";
		String server = ConfigLoader.getServer();
		String sender = ConfigLoader.getSender();
		String username = ConfigLoader.getUsername();
		String password = ConfigLoader.getPassword();
		String nickname = ConfigLoader.getNickname();
		MailSender mail = new MailSender(server);
		mail.setNeedAuth(true);
		mail.setNamePass(username, password, nickname);
		maiBody = TemplateFactory.generateHtmlFromFtlWithMapObject(templateName, map);
		mail.setSubject(subject);
		mail.setBody(maiBody);
		mail.setReceiver(receiver);
		mail.setSender(sender);
		mail.sendout();
	}
	
	/**
	 * 根据模板名称查找模板，加载模板内容后发送邮件
	 * 
	 * @Date:2014年4月26日 上午11:02:58
	 * @author 曹志飞
	 * @param receiver
	 *            收件人地址
	 * @param subject
	 *            邮件主题
	 * @param map
	 *            邮件内容与模板内容转换对象
	 * @param templateName
	 *            模板文件名称
	 * @throws IOException
	 * @throws TemplateException
	 * @throws MessagingException
	 * @Description:
	 * @return void
	 */
	public static void sendMailAndFileByTemplate(String receiver,
			String subject, String filePath, Map<String, String> map,
			String templateName) throws IOException, TemplateException,
			MessagingException {
		String maiBody = "";
		String server = ConfigLoader.getServer();
		String sender = ConfigLoader.getSender();
		String username = ConfigLoader.getUsername();
		String password = ConfigLoader.getPassword();
		String nickname = ConfigLoader.getNickname();
		MailSender mail = new MailSender(server);
		mail.setNeedAuth(true);
		mail.setNamePass(username, password, nickname);
		maiBody = TemplateFactory.generateHtmlFromFtl(templateName, map);
		mail.setSubject(subject);
		mail.addFileAffix(filePath);
		mail.setBody(maiBody);
		mail.setReceiver(receiver);
		mail.setSender(sender);
		mail.sendout();
	}

	/**
	 * 普通方式发送邮件内容
	 * 
	 * @Date:2014年4月26日 下午1:20:47
	 * @author 曹志飞
	 * @param receiver
	 *            收件人地址
	 * @param subject
	 *            邮件主题
	 * @param maiBody
	 *            邮件正文
	 * @throws IOException
	 * @throws MessagingException
	 * @Description:
	 * @return void
	 */
	public static void sendMail(String receiver, String subject, String maiBody)
			throws IOException, MessagingException {
		String server = ConfigLoader.getServer();
		String sender = ConfigLoader.getSender();
		String username = ConfigLoader.getUsername();
		String password = ConfigLoader.getPassword();
		String nickname = ConfigLoader.getNickname();
		MailSender mail = new MailSender(server);
		mail.setNeedAuth(true);
		mail.setNamePass(username, password, nickname);
		mail.setSubject(subject);
		mail.setBody(maiBody);
		mail.setReceiver(receiver);
		mail.setSender(sender);
		mail.sendout();
	}

	/**
	 * 普通方式发送邮件内容，并且附带文件附件
	 * 
	 * @Date:2014年4月26日 下午1:20:47
	 * @author 曹志飞
	 * @param receiver
	 *            收件人地址
	 * @param subject
	 *            邮件主题
	 * @param filePath
	 *            文件的绝对路径
	 * @param maiBody
	 *            邮件正文
	 * 
	 * @throws IOException
	 * @throws MessagingException
	 * @Description:
	 * @return void
	 */
	public static void sendMailAndFile(String receiver, String subject,
			String filePath, String maiBody) throws IOException,
			MessagingException {
		String server = ConfigLoader.getServer();
		String sender = ConfigLoader.getSender();
		String username = ConfigLoader.getUsername();
		String password = ConfigLoader.getPassword();
		String nickname = ConfigLoader.getNickname();
		MailSender mail = new MailSender(server);
		mail.setNeedAuth(true);
		mail.setNamePass(username, password, nickname);
		mail.setSubject(subject);
		mail.setBody(maiBody);
		mail.addFileAffix(filePath);
		mail.setReceiver(receiver);
		mail.setSender(sender);
		mail.sendout();
	}

}
