package com.pulawskk.demobootstrap4portfolio.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class HomeController {

    @Value("classpath:data/Pulawski_Karol_CV.pdf")
    private Resource resourceFile;

    @GetMapping({"/"})
    public String indexPage() {
        return "index";
    }

    @GetMapping("/download")
    @ResponseBody
    public ResponseEntity downloadCv() {
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachement; filename=Pulawski_Karol_CV.pdf")
                .body(resourceFile);
    }
}
