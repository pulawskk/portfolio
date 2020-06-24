package com.pulawskk.demobootstrap4portfolio.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class HomeControllerTest {

    @InjectMocks
    HomeController homeController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(homeController).build();
    }

    @Test
    void shouldDisplayIndexPage_whenIndexPageIsCalled() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    void shouldDownloadFile_whenDownloadFileIsInvoked() throws Exception {
        mockMvc.perform(get("/download"))
                .andExpect(header().string(HttpHeaders.CONTENT_DISPOSITION.toString(),"attachement; filename=cv.pdf"))
                .andExpect(status().isOk());
    }
}