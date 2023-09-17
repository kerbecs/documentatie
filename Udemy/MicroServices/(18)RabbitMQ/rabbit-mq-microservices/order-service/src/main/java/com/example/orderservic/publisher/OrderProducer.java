package com.example.orderservic.publisher;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class OrderProducer {
    @Value("${spring.rabbitmq.exchange.name}")
    private String exchange;
    @Value("${spring.rabbitmq.routing-key.order.name}")
    private String orderRoutingKey;
    @Value("${spring.rabbitmq.routing-key.email.name}")
    private String messageRoutingKey;
    private final Logger logger = Logger.getLogger(OrderProducer.class.getName());

    private final RabbitTemplate rabbitTemplate;

    public void sendOrder(OrderEvent orderEvent){
        logger.info(String.format("Order event sent => %s",orderEvent));

        rabbitTemplate.convertAndSend(exchange,orderRoutingKey,orderEvent);
        rabbitTemplate.convertAndSend(exchange,messageRoutingKey,orderEvent);
    }
}
