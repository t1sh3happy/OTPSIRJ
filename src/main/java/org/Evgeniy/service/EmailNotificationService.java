package org.Evgeniy.service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailNotificationService implements NotificationService {
    private String username;
    private String password;
    private String fromEmail;
    private Session session;

    public EmailNotificationService() {
        Properties config = loadConfig();
        this.username = config.getProperty("email.username");
        this.password = config.getProperty("email.password");
        this.fromEmail = config.getProperty("email.from");
        this.session = Session.getInstance(config, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }

    private Properties loadConfig() {
        try {
            Properties props = new Properties();
            props.load(EmailNotificationService.class.getClassLoader().getResourceAsStream("email.properties"));
            return props;
        } catch (Exception e) {
            throw new RuntimeException("Failed to load email configuration", e);
        }
    }

    @Override
    public void sendOTP(String toEmail, String otpCode) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            message.setSubject("Your OTP Code");
            message.setText("Your verification code is: " + otpCode);

            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }
}