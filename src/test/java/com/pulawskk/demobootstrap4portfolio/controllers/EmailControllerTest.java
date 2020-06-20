package com.pulawskk.demobootstrap4portfolio.controllers;

import com.pulawskk.demobootstrap4portfolio.models.Email;
import com.pulawskk.demobootstrap4portfolio.services.JmsBrokerService;
import com.pulawskk.demobootstrap4portfolio.services.impl.EmailServiceImpl;
import com.pulawskk.demobootstrap4portfolio.services.impl.RabbitMQService;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class EmailControllerTest {

    private static final String MY_EMAIL = "pulawskk@gmail.com";

    @InjectMocks
    EmailController emailController;

    @Mock
    EmailServiceImpl emailService;

    @Mock
    RabbitMQService rabbitMQService;

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

    @Test
    void shouldGotEmailsFromQueue_whenCheckEmailsIsCalled() throws Exception {
        //given
        List<Email> emails = Lists.newArrayList();
        when(rabbitMQService.collectEmails()).thenReturn(emails);

        //when
        mockMvc.perform(get("/emails")
                    .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
//                .andExpect(jsonPath("$[0]['id']", is(inplayGames.get(0).getId().intValue())))
                .andExpect(status().isOk());

        //then
        verify(rabbitMQService, times(1)).collectEmails();
    }
}