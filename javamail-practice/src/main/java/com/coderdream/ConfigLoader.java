package com.coderdream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 邮件发送配置信息加载类
 * 
 * @date 2014年4月26日 下午2:52:06
 * @author 曹志飞
 * @Description:
 * @project mailUtil
 */
public class ConfigLoader {
	//日志记录对象
	private static Logger log = LoggerFactory.getLogger(ConfigLoader.class);
	// 配置文件路径
	private static String mailPath = "mail/mail.properties";
	// 邮件发送SMTP主机
	private static String server;
	// 发件人邮箱地址
	private static String sender;
	// 发件人邮箱用户名
	private static String username;
	// 发件人邮箱密码
	private static String password;
	// 发件人显示昵称
	private static String nickname;

	// 收件人显示昵称
	private static String mailto;
	
	static {
		// 类初始化后加载配置文件
		InputStream in = ConfigLoader.class.getClassLoader()
				.getResourceAsStream(mailPath);
		Properties props = new Properties();
		try {
			props.load(in);
		} catch (IOException e) {
			log.error("load mail setting error,pleace check the file path:"
					+ mailPath);
			log.error(e.toString(), e);
		}
		server = props.getProperty("mail.server");
		sender = props.getProperty("mail.sender");
		username = props.getProperty("mail.username");
		password = props.getProperty("mail.password");
		nickname = props.getProperty("mail.nickname");
		mailto = props.getProperty("mail.mailto");
		log.debug("load mail setting success,file path:" + mailPath);
	}

	public static String getServer() {
		return server;
	}

	public static String getSender() {
		return sender;
	}

	public static String getUsername() {
		return username;
	}

	public static String getPassword() {
		return password;
	}

	public static String getNickname() {
		return nickname;
	}

	public static void setMailPath(String mailPath) {
		ConfigLoader.mailPath = mailPath;
	}

	public static Logger getLog() {
		return log;
	}

	public static void setLog(Logger log) {
		ConfigLoader.log = log;
	}

	public static String getMailto() {
		return mailto;
	}

	public static void setMailto(String mailto) {
		ConfigLoader.mailto = mailto;
	}

	public static String getMailPath() {
		return mailPath;
	}

	public static void setServer(String server) {
		ConfigLoader.server = server;
	}

	public static void setSender(String sender) {
		ConfigLoader.sender = sender;
	}

	public static void setUsername(String username) {
		ConfigLoader.username = username;
	}

	public static void setPassword(String password) {
		ConfigLoader.password = password;
	}

	public static void setNickname(String nickname) {
		ConfigLoader.nickname = nickname;
	}

}
