package com.peczedavid;

import com.peczedavid.config.RabbitMQConfing;
import com.peczedavid.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    Logger logger = LoggerFactory.getLogger(MessageListener.class);

    @RabbitListener(queues = RabbitMQConfing.MESSAGE_QUEUE_NAME)
    public void listener(Message message) {
        logger.info(message.getMessage());
    }

}
