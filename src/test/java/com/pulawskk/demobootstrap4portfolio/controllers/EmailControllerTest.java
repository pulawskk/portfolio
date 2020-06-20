package com.pulawskk.demobootstrap4portfolio.controllers;

import com.pulawskk.demobootstrap4portfolio.services.impl.EmailServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
class EmailControllerTest {

    private static final String MY_EMAIL = "pulawskk@gmail.com";

    @InjectMocks
    EmailController emailController;

    @Mock
    EmailServiceImpl emailService;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(emailController).build();
    }

    @Test
    void shouldSendEmail_whenPostRequestIsCalledOnSendEmail() throws Exception {
        //given
        final String name = "karol";
        final String email = "karol@gmail.com";
        final String subject = "subject: " + name;
        final String message = "simple message content";
        final String phone = "12345";

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(message)
                .append("\n\n")
                .append("from: " + email)
                .append("\n\n")
                .append("phone number: " + phone)
                .append("\n\n");
        final String text = stringBuilder.toString();

        //when
        mockMvc.perform(post("/sendEmail")
                    .param("name", name)
                    .param("email", email)
                    .param("message", message)
                    .param("phone", phone))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:#contact"));

        //then
        verify(emailService,times(1)).sendSimpleMessage(MY_EMAIL, subject, text);
    }
}