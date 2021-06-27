package com.qzy.springboot.entities;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Data
public class Email {
    private String senderName;

    private String senderEmail;

    private String senderText;

    @Value("${spring.mail.username}")
    private String myEmail;


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
