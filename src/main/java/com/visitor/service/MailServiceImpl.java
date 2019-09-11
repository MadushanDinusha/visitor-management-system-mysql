package com.visitor.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

@Service
public class MailServiceImpl implements MailService {

    @Value("${mail.sender.address}")
    private String senderEmail;
    @Value("${mail.sender.password}")
    private String senderPassword;
    @Value("${mail.receiver.list.to}")
    private String receiverTO;
    @Value("${mail.receiver.list.cc}")
    private String receiverCC;

    public void sendmail(String userName,String contextPath) throws AddressException, MessagingException, IOException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(senderEmail, false));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiverTO));
        msg.setSubject("Visitor Request");
        msg.setContent(userName + " has sent a new request \n link here - "+contextPath+"/admin/getRequests ", "text/html");
        msg.setSentDate(new Date());
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent("Tutorials point email", "text/html");
        Transport.send(msg);
    }
}