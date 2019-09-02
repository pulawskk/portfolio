package com.pulawskk.demobootstrap4portfolio.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/index")
    public String indexPage() {
        return "index";
    }
}
