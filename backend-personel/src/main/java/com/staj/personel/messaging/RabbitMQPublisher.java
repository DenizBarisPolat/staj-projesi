package com.staj.personel.messaging;

import com.staj.personel.messaging.dto.NotificationMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQPublisher {

    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.queue}")
    private String queueName;

    public RabbitMQPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(NotificationMessage message) {
        rabbitTemplate.convertAndSend(queueName, message); 
    }
}
