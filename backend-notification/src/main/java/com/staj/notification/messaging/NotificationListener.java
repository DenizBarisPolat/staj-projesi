package com.staj.notification.messaging;

import com.staj.notification.messaging.dto.NotificationMessage;
import com.staj.notification.service.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationListener {

    private final EmailService emailService;

    public NotificationListener(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = "${rabbitmq.queue}")
    public void handle(NotificationMessage message) {

        emailService.sendEventEmail(message);

        System.out.println("[notification] Email sent for event: " + message.getEvent() + " id=" + message.getId());
    }
}
