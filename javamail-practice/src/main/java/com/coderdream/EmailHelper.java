package com.coderdream;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class EmailHelper {
    
    private String host;
    private String username;
    private String password;
    private String from;
    
    private String to;
    private String subject;
    private String htmlContent;
    private String imagePath;
    
    public EmailHelper(String host, String username, String password, String from) throws AddressException, MessagingException{
        this.host = host;
        this.username = username;
        this.password = password;
        this.from = from;
    }
    
	public void sendWithImage() throws Exception {

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", host);

        final String username1 = username;
        final String password1 = password;

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username1, password1);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(subject);
        Multipart multipart = new MimeMultipart("related");

        System.out.println(" html ");
        BodyPart htmlPart = new MimeBodyPart();
        htmlContent = "<img src=\"cid:image\">" + htmlContent;
        htmlPart.setContent(htmlContent, "text/html");
        multipart.addBodyPart(htmlPart);
        
        System.out.println(" image ");
        System.out.println("image path : " + imagePath);
        BodyPart imgPart = new MimeBodyPart();
        DataSource fds = new FileDataSource(this.imagePath);

        imgPart.setDataHandler(new DataHandler(fds));
        imgPart.setHeader("Content-ID", "<image>");

        multipart.addBodyPart(imgPart);
        message.setContent(multipart);
        Transport.send(message);

        System.out.println(" Sent -| ");
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
    }
    
    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
