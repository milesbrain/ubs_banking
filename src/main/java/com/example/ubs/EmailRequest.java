package com.example.ubs;

public class EmailRequest {
    private String email;
    private String content;

    // Constructors, getters, and setters
    public EmailRequest() {
    }

    public EmailRequest(String email, String content) {
        this.email = email;
        this.content = content;
    }

    // Getters and setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
