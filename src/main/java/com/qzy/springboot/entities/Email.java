package com.qzy.springboot.entities;

public class Email {
    private String senderName;
    private String senderEmail;
    private String senderText;
    private final String myEmail = "your_email";

    public Email(String senderName, String senderEmail, String senderText) {
        this.senderName = senderName;
        this.senderEmail = senderEmail;
        this.senderText = senderText;
    }

    public String getSubject() {
        return "A Message From " + senderName + " | Email: " + senderEmail;
    }

    public String getMyEmail() {
        return myEmail;
    }

    public String getSenderText() {
        return senderText;
    }
}
