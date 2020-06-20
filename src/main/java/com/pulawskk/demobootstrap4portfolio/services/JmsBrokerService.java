package com.pulawskk.demobootstrap4portfolio.services;

import com.pulawskk.demobootstrap4portfolio.models.Email;

import java.util.List;

public interface JmsBrokerService {

    boolean publish(String msg);

    List<Email> collectEmails();
}
