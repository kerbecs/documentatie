package com.example.orderservic.publisher;

import com.example.orderservic.dto.OrderDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderEvent {
    private String status;
    private String message;
    private OrderDto orderDto;
}
