package com.example.stockservic.consumer;


import com.example.stockservic.dto.OrderDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class OrderConsumer {
    private final Logger logger = Logger.getLogger(OrderConsumer.class.getName());

    @RabbitListener(queues = "${spring.rabbitmq.queue.order.name}")
    public void getOrder(OrderDto orderDto){
        logger.info(String.format("Order event received => %s",orderDto));
    }
}
