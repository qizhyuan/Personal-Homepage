package com.qzy.springboot.service;

import com.qzy.springboot.entities.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class EmailServiceImpl implements EmailService{
    @Autowired
    private JavaMailSender javaMailSender;
    private static EmailServiceImpl emailService;
    @PostConstruct
    public void init() {
        emailService = this;
        emailService.javaMailSender = this.javaMailSender;
    }
    @Override
    public void sendMessage(Email email) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject(email.getSubject());
        simpleMailMessage.setFrom(email.getMyEmail());
        simpleMailMessage.setTo("mdshxy@qq.com");
        simpleMailMessage.setText(email.getSenderText());
        emailService.javaMailSender.send(simpleMailMessage);
    }
}
