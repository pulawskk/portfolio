package com.pulawskk.demobootstrap4portfolio.controllers;

import com.pulawskk.demobootstrap4portfolio.services.PropertyServiceForJasyptStarter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/index")
    public String indexPage() {


        return "index";
    }
}
