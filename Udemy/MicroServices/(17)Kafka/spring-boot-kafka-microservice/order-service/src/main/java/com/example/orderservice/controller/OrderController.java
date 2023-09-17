package com.example.orderservice.controller;

import com.example.basedomains.dto.OrderDto;
import com.example.basedomains.dto.OrderEvent;
import com.example.orderservice.kafka.OrderProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class OrderController {
    private final OrderProducer orderProducer;

    @PostMapping("/orders")
    public String placeOrder(@RequestBody OrderDto order){
        order.setOrderId(UUID.randomUUID().toString());

        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setStatus("PENDING");
        orderEvent.setMessage("order status is in pending state");
        orderEvent.setOrderDto(order);

        orderProducer.sendMessage(orderEvent);

        return "Order placed successfully";

    }

}
