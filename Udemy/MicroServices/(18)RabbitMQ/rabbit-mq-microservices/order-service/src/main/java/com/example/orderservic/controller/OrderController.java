package com.example.orderservic.controller;

import com.example.orderservic.dto.OrderDto;
import com.example.orderservic.publisher.OrderEvent;
import com.example.orderservic.publisher.OrderProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class OrderController {
    private final OrderProducer orderProducer;

    @PostMapping("/orders")
    public ResponseEntity<OrderDto> sendOrder(@RequestBody OrderDto orderDto){
        OrderEvent orderEvent = new OrderEvent();
        orderDto.setOrderId(UUID.randomUUID().toString());
        orderEvent.setOrderDto(orderDto);
        orderEvent.setStatus("PENDING");
        orderEvent.setMessage("Order is in pending status");

        orderProducer.sendOrder(orderEvent);

        return ResponseEntity.ok(orderDto);
    }
}
