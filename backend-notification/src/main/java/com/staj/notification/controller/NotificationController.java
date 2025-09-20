package com.staj.notification.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final RabbitTemplate rabbitTemplate;

    public NotificationController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping
    public String sendNotification(@RequestBody String message) {
        rabbitTemplate.convertAndSend("personelQueue", message);
        return "Notification sent: " + message;
    }
}
