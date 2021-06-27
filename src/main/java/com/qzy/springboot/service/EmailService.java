package com.qzy.springboot.service;

import com.qzy.springboot.entities.Email;

public interface EmailService {
    void sendMessage(Email email);
}
