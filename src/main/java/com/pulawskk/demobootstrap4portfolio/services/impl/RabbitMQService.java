package com.pulawskk.demobootstrap4portfolio.services.impl;

import com.pulawskk.demobootstrap4portfolio.models.Email;
import com.pulawskk.demobootstrap4portfolio.services.JmsBrokerService;
import com.rabbitmq.client.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

@Service
public class RabbitMQService implements JmsBrokerService {

    @Value("${custom.rabbit.queue}")
    private String QUEUE_NAME;

    private final ConnectionFactory connectionFactory;

    private final String URI = "amqp://faxkjnah:bzQxTd730KuT-OBhWEyvOJDaBojawrj6@roedeer.rmq.cloudamqp.com/faxkjnah";

    @Value("${spring.rabbitmq.host}")
    private String localHost;

    public RabbitMQService() {
        this.connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(localHost);
    }

    @Override
    public boolean publish(String msg) {
        try {
            connectionFactory.setUri(java.net.URI.create(URI));
        } catch (URISyntaxException | KeyManagementException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        boolean wasSent = false;

        try (Connection connection = connectionFactory.newConnection()) {
            Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
            wasSent = true;
        } catch (TimeoutException | IOException e) {
            e.printStackTrace();
        }

        return wasSent;
    }

    @Override
    public List<Email> collectEmails() {
        List<Email> emails = new ArrayList<>();

        Connection connection = null;
        try {
            connectionFactory.setUri(java.net.URI.create(URI));
            connection = connectionFactory.newConnection();

            Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String message = new String(body, "UTF-8");
                    System.out.println("JMS game message: " + message);
                }
            };
            channel.basicConsume(QUEUE_NAME, true, consumer);

        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return emails;
    }
}
