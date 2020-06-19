package com.pulawskk.demobootstrap4portfolio.services.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EmailServiceImplTest {

    private final static String EMAIL_FROM = "pulawskk@gmail.com";

    @InjectMocks
    EmailServiceImpl emailService;

    @Mock
    JavaMailSenderImpl javaMailSender;

    SimpleMailMessage message;

    @BeforeEach
    void setUp() {
        message = new SimpleMailMessage();
    }

    @Test
    void shouldSendEmail_whenEmailHasConfiguredProperly() {
        //given
        message.setTo("example@mail.com");
        message.setSubject("subject-test");
        message.setText("text of message");
        message.setFrom(EMAIL_FROM);

        //when
        emailService.sendSimpleMessage(message.getTo()[0], message.getSubject(), message.getText());

        //then
        verify(javaMailSender, times(1)).send(message);
    }
}