package com.example.ubs;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class Email {

    private final JavaMailSender javaMailSender;
    private final String fromEmail;

    public Email(JavaMailSender javaMailSender, @Value("${email.address}") String fromEmail) {
        this.javaMailSender = javaMailSender;
        this.fromEmail = fromEmail;
    }

    @Async
    public void send(String to, String content) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(content, true);
            helper.setTo(to);
            helper.setSubject("Confirm your email");
            helper.setFrom(fromEmail);

            // Debugging properties, ensure they are set in your application properties
            System.setProperty("javax.net.ssl.trustStore", "C:\\Program Files\\Java\\jdk-20\\lib\\security\\cacerts");
            System.setProperty("javax.net.ssl.trustStorePassword", "changeit");

            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new IllegalStateException("Failed to send email", e);
        }
    }
}
