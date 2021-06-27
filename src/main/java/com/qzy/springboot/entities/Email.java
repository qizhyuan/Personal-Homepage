package com.qzy.springboot.entities;

import lombok.Data;

@Data
public class Email {
    private String senderName;

    private String senderEmail;

    private String senderText;

    public String getSubject() {
        return "A Message From " + senderName + " | Email: " + senderEmail;
    }
}
