package com.qzy.springboot.service;

import com.qzy.springboot.entities.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService{
    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendMessage(Email email) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject(email.getSubject());
        simpleMailMessage.setFrom(email.getSenderEmail());
        simpleMailMessage.setTo(email.getMyEmail());
        simpleMailMessage.setText(email.getSenderText());
        javaMailSender.send(simpleMailMessage);
    }
}
