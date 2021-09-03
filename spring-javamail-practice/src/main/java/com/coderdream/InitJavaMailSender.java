package com.coderdream;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author ：CoderDream
 * @date ：Created in 2021/9/3 16:36
 * @description：
 * @modified By：CoderDream
 * @version: $
 */
public class InitJavaMailSender {
    /**
     * 此类的作用是加载配置文件来创建JavaMailSender对象  JavaMailSenderImpl是该接口的实现类
     * 因此我们需要对JavaMailSenderImpl进行配置 在spring的环境下 我们可以使用加载配置文件的方式 或者
     * 在spring配置文件中直接配置该bean（由spring托管）使用时@Autowired直接注入即可
     * <bean id="javaMailSender" class="org.springframework.mail.javamail.JavaMailSenderImpl">
     * 		<property name="host" value="${mail.smtp.host}" />
     * 		<property name="username" value="${mail.smtp.username}" />
     * 		<property name="password" value="${mail.smtp.password}" />
     * 		<property name="defaultEncoding" value="${mail.smtp.defaultEncoding}" />
     * 		<property name="javaMailProperties">
     * 			<props>
     * 				<prop key="mail.smtp.auth">${mail.smtp.auth}</prop>
     * 				<prop key="mail.smtp.timeout">${mail.smtp.timeout}</prop>
     * 			    ...
     * 			</props>
     * 		</property>
     * 	</bean>
     * 本次采用加载配置文件的方式
     * 由spring提供 其底层使用的仍然是javax.mail进行邮件的发送
     * JavaMailSenderImpl 依赖javax.mail 和 spring-context-support包
     */
    // 传输协议
    private static String protocol;
    // 服务器主机名
    private static String host;
    // 服务器端口号
    private static String port;
    // 是否进行用户名密码校验
    private static String auth;
    // 设置是否使用ssl安全连接
    private static String enableSSL;
    // 设置是否显示debug信息
    private static String debug;
    // 超时时间
    private static String timeout;
    // 编码格式
    private static String defaultEncoding;
    // 邮箱地址
    public static String username;
    // 授权码
    private static String password;

    private static volatile JavaMailSenderImpl javaMailSenderImpl;

    static {
        init();
    }

    private static void init() {
        // 加载配置文件
        Properties properties = new Properties();
        InputStream resourceAsStream = JavaMailSender.class.getClassLoader().getResourceAsStream("mail.properties");
        try {
            properties.load(resourceAsStream);
            protocol = properties.getProperty("mail.transport.protocol");
            host = properties.getProperty("mail.smtp.host");
            port = properties.getProperty("mail.smtp.port");
            auth = properties.getProperty("mail.smtp.auth");
            enableSSL = properties.getProperty("mail.smtp.ssl.enable");
            debug = properties.getProperty("mail.debug");
            timeout = properties.getProperty("mail.smtp.timeout");
            defaultEncoding = properties.getProperty("mail.smtp.defaultEncoding");
            username = properties.getProperty("mail.smtp.username");
            password = properties.getProperty("mail.smtp.password");
            System.out.println("mail.properties加载成功");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("mail.properties加载失败");
        } finally {
            if (null != resourceAsStream) {
                try {
                    resourceAsStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 使用单例获取JavaMailSender 双重检查既保证的线程安全 又保证了效率 又能起到延迟加载的作用
     * @return
     */
    public static JavaMailSender getJavaMailSender() {
        if (null == javaMailSenderImpl) {
            synchronized (InitJavaMailSender.class) {
                if (null == javaMailSenderImpl) {
                    javaMailSenderImpl = new JavaMailSenderImpl();
                    javaMailSenderImpl.setProtocol(protocol);
                    javaMailSenderImpl.setHost(host);
                    javaMailSenderImpl.setPort(Integer.parseInt(port));
                    javaMailSenderImpl.setDefaultEncoding(defaultEncoding);
                    javaMailSenderImpl.setUsername(username);
                    javaMailSenderImpl.setPassword(password);
                    Properties properties = new Properties();
                    properties.setProperty("mail.smtp.auth", auth);
                    properties.setProperty("mail.smtp.ssl.enable", enableSSL);
                    properties.setProperty("mail.debug", debug);
                    properties.setProperty("mail.smtp.timeout", timeout);
                    javaMailSenderImpl.setJavaMailProperties(properties);
                }
            }
        }
        return javaMailSenderImpl;
    }

}