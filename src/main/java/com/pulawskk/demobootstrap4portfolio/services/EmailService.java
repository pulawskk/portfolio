package com.pulawskk.demobootstrap4portfolio.services;

import com.pulawskk.demobootstrap4portfolio.models.Email;

import java.util.List;

public interface EmailService {

    void sendSimpleMessage(String to, String subject, String text);
}
