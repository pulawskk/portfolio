package com.pulawskk.demobootstrap4portfolio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoBootstrap4PortfolioApplication {

    public static void main(String[] args) {
        System.setProperty(args[0], args[1]);
        SpringApplication.run(DemoBootstrap4PortfolioApplication.class, args);
    }

}
