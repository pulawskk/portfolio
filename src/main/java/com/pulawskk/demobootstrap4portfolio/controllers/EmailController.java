package com.pulawskk.demobootstrap4portfolio.controllers;

import com.pulawskk.demobootstrap4portfolio.models.Email;
import com.pulawskk.demobootstrap4portfolio.services.EmailService;
import com.pulawskk.demobootstrap4portfolio.services.JmsBrokerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class EmailController {

    private final EmailService emailService;
    private final JmsBrokerService jmsBrokerService;

    private static final String MY_EMAIL = "pulawskk@gmail.com";

    public EmailController(EmailService emailService, JmsBrokerService jmsBrokerService) {
        this.emailService = emailService;
        this.jmsBrokerService = jmsBrokerService;
    }

    @PostMapping("/sendEmail")
    public String sendEmail(@RequestParam Map<String, String> requestParams) {
        String name = requestParams.get("name");
        String email = requestParams.get("email");
        String message = requestParams.get("message");
        String phone = requestParams.get("phone");

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(message)
                .append("\n\n")
                .append("from: " + email)
                .append("\n\n")
                .append("phone number: " + phone)
                .append("\n\n");
        emailService.sendSimpleMessage(MY_EMAIL, "subject: " + name, stringBuilder.toString());
        return "redirect:#contact";
    }

    @GetMapping(value = "/emails", produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<Email>> checkEmails() {
        List<Email> emails = jmsBrokerService.collectEmails();
        return new ResponseEntity<>(emails, HttpStatus.OK);
    }
}
