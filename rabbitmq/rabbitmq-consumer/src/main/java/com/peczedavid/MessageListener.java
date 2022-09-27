package com.peczedavid;

import com.peczedavid.config.RabbitMQConfing;
import com.peczedavid.model.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    @RabbitListener(queues = RabbitMQConfing.MESSAGE_QUEUE_NAME)
    public void listener(Message message) {
        System.out.println(message.getMessageDate() + " MESSAGE --- " + message.getMessage());
    }

}
