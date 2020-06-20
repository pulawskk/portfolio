package com.pulawskk.demobootstrap4portfolio.services.impl;

import com.pulawskk.demobootstrap4portfolio.models.Email;
import com.pulawskk.demobootstrap4portfolio.services.EmailService;
import com.pulawskk.demobootstrap4portfolio.services.JmsBrokerService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;
    private final JmsBrokerService jmsService;

    public EmailServiceImpl(JavaMailSender javaMailSender, JmsBrokerService jmsService) {
        this.javaMailSender = javaMailSender;
        this.jmsService = jmsService;
    }

    @Override
    public void sendSimpleMessage(String to, String subject, String text) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        message.setFrom("pulawskk@gmail.com");

        javaMailSender.send(message);
        jmsService.publish(message.toString());
    }
}
