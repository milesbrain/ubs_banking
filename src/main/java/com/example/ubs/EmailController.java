package com.example.ubs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    private Email emailService;

    @PostMapping("/send-email")
    public String sendEmail(@RequestBody EmailRequest request) {
        emailService.send(request.getEmail(), request.getContent());
        return "Email sent successfully";
    }
}
