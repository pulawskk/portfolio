package com.pulawskk.demobootstrap4portfolio;

import com.pulawskk.demobootstrap4portfolio.services.PropertyServiceForJasyptStarter;
import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@PropertySource("encrypted.properties")
public class WebAppConfig extends WebMvcConfigurerAdapter {

    @Bean
    public LayoutDialect layoutDialect() {
        return new LayoutDialect();
    }
}
