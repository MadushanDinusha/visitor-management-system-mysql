package com.visitor.service;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.io.IOException;

public interface MailService {
    void sendmail(String email) throws AddressException, MessagingException, IOException;
}
