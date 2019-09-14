package com.pulawskk.demobootstrap4portfolio.controllers;

import com.pulawskk.demobootstrap4portfolio.services.EmailService;
import com.pulawskk.demobootstrap4portfolio.services.PropertyServiceForJasyptStarter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class EmailController {

    private final PropertyServiceForJasyptStarter propertyServiceForJasyptStarter;
    private final EmailService emailService;

    private static final String MY_EMAIL = "pulawskk@gmail.com";

    public EmailController(PropertyServiceForJasyptStarter propertyServiceForJasyptStarter, EmailService emailService) {
        this.propertyServiceForJasyptStarter = propertyServiceForJasyptStarter;
        this.emailService = emailService;
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
        return "redirect:index#contact";
    }
}
