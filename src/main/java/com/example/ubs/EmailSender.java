package com.example.ubs;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailSender {

    @Async
    public void send(String to, String content) {
        // Implementation to send an email asynchronously
        // You can use JavaMailSender or any other email sending mechanism here
        // This is just a placeholder implementation
        System.out.println("Sending email to: " + to);
        System.out.println("Email content: " + content);
        // Actual sending logic goes here
    }

    public void Send(String email, String content) {
        // This method can be removed if not used
    }
}
