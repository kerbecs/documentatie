package com.example.emailservice.kafka;

import com.example.basedomains.dto.OrderEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class OrderConsumer {
    private final Logger logger = Logger.getLogger(OrderConsumer.class.getName());

    @KafkaListener(
            groupId = "${spring.kafka.consumer.group-id}",
            topics = "${spring.kafka.topic.name}")
    public void consume(OrderEvent orderEvent){
        logger.info(String.format("Order event received in email service => %s",orderEvent));
        // send an email to a customer

    }
}
