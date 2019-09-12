package com.pulawskk.demobootstrap4portfolio.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class PropertyServiceForJasyptStarter {

//    @Value("${spring.mail.password}")
    @Value("spring.mail.password")
    private String property;

    public String getProperty() {
        return property;
    }

    public String getPasswordUsingEnvironment(Environment environment) {
        return environment.getProperty("spring.mail.password");
    }
}
