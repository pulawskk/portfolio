package com.pulawskk.demobootstrap4portfolio.services.impl;

import com.pulawskk.demobootstrap4portfolio.services.JmsBrokerService;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

@Service
public class RabbitMQService implements JmsBrokerService {

    @Value("${custom.rabbit.queue}")
    private String QUEUE_NAME;

    private final String URI = "amqp://faxkjnah:bzQxTd730KuT-OBhWEyvOJDaBojawrj6@roedeer.rmq.cloudamqp.com/faxkjnah";

    @Value("${spring.rabbitmq.host}")
    private String localHost;

    @Override
    public boolean publish(String msg) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(localHost);
        try {
            connectionFactory.setUri(java.net.URI.create(URI));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }

        boolean wasSent = false;

        try (Connection connection = connectionFactory.newConnection()) {
            Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
            wasSent = true;
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return wasSent;
    }
}
