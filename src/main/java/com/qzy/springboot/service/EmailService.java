package com.qzy.springboot.service;

import com.qzy.springboot.entities.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService{
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String myEmail;

    public void sendMessage(Email email) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject(email.getSubject());
        simpleMailMessage.setFrom(myEmail);
        simpleMailMessage.setTo(myEmail);
        simpleMailMessage.setText(email.getSenderText());
        System.out.println(email);
        try {
            javaMailSender.send(simpleMailMessage);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
