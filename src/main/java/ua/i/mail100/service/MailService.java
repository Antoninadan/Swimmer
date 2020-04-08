package ua.i.mail100.service;

import org.springframework.beans.factory.annotation.Autowired;
import ua.i.mail100.config.MailConfig;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailService {
    MailConfig mailConfig = new MailConfig();

    public void sendMail(String to, String subject, String text) {
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.auth", mailConfig.smtpAuth);
        properties.setProperty("mail.smtp.starttls.enable", mailConfig.smtpStarttlsEnable);
        properties.setProperty("mail.smtp.host", mailConfig.smtpHost);
        properties.setProperty("mail.smtp.port", mailConfig.smtpPort);

        Session session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(mailConfig.username, mailConfig.password);
                    }
                });

        try {
            MimeMessage message = new MimeMessage(session); // email message
            message.setFrom(new InternetAddress(mailConfig.from)); // setting header fields
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            message.setText(text);

            Transport.send(message);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
