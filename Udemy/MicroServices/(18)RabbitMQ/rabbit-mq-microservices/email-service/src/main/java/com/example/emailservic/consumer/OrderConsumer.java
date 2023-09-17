package com.example.emailservic.consumer;

import com.example.emailservic.publisher.OrderEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class OrderConsumer {
    private final Logger logger = Logger.getLogger(OrderConsumer.class.getName());

    @RabbitListener(queues = "${spring.rabbitmq.queue.email.name}")
    public void consumeOrder(OrderEvent orderEvent){
        logger.info(String.format("Order consumed in message => %s",orderEvent));
    }
}
