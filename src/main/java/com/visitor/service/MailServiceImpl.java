package com.visitor.service;

import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

@Service
public class MailServiceImpl implements MailService {
    public void sendmail(String userName) throws AddressException, MessagingException, IOException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.office365.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("", "");
            }
        });
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("", false));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("hashand@brandix.com"));
        msg.setSubject("New Request");
        msg.setContent(userName + " has sent a new request \n link here - http://localho st:8080/visitor-manage/admin/getRequests ", "text/html");
        msg.setSentDate(new Date());
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent("Tutorials point email", "text/html");
        Transport.send(msg);
    }
}