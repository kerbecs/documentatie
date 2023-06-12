package com.example.demo.custom_exception;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EntityException {
    private Integer status;
    private String message;
    private Long timeStamp;
}
