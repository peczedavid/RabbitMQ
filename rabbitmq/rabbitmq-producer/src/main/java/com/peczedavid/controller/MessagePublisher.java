package com.peczedavid.controller;

import com.peczedavid.config.RabbitMQConfing;
import com.peczedavid.model.Message;
import org.jetbrains.annotations.NotNull;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
public class MessagePublisher {

    @Autowired
    private RabbitTemplate template;

    @PostMapping("/message/publish")
    public String publishMessage(@RequestBody @NotNull Message message) {
        message.setMessageId(UUID.randomUUID().toString());
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-HH HH:mm:ss.SSS");
        LocalDateTime now = LocalDateTime.now();
        message.setMessageDate(dateTimeFormatter.format(now));
        template.convertAndSend(RabbitMQConfing.MESSAGE_EXCHANGE_NAME,
                                RabbitMQConfing.MESSAGE_ROUTING_KEY,
                                message);
        return "Message published";
    }

}
