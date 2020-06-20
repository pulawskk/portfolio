package com.pulawskk.demobootstrap4portfolio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Map;

@SpringBootApplication
public class DemoBootstrap4PortfolioApplication {

    public static void main(String[] args) {
        Map<String, String> properties = System.getenv();
        String passToEncrypt = properties.get("PASS_TO_ENCRYPT");
        System.setProperty("spring.mail.password", passToEncrypt);

        String passToAmqp = properties.get("PASS_TO_AMQP");
        System.setProperty("spring.rabbitmq.password", passToAmqp);
        SpringApplication.run(DemoBootstrap4PortfolioApplication.class, args);
    }
}
