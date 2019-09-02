package com.pulawskk.demobootstrap4portfolio.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class EmailController {

    @PostMapping("/sendEmail")
    public String sendEmail(@RequestParam Map<String, String> requestParams) {
        String name = requestParams.get("name");
        String email = requestParams.get("email");
        String message = requestParams.get("message");
        String phone = requestParams.get("phone");
        System.out.println(name + " | " + email + " | " + message + " | " + phone);
        return "redirect:index#contact";
    }
}
