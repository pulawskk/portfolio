package com.pulawskk.demobootstrap4portfolio;

import com.ulisesbocchio.jasyptspringboot.properties.JasyptEncryptorConfigurationProperties;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = { AppConfigForJasyptStarter.class })
class DemoBootstrap4PortfolioApplicationTests {

    @Test
        void contextLoads() {
    }

}
