package com.example.demo.exception_handling;

import com.example.demo.custom_exception.EntityException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalHandlerException {
    @ExceptionHandler
    public ResponseEntity<EntityException> unknownException(Exception e) {
        return createResponseEntity(HttpStatus.SERVICE_UNAVAILABLE, e.getMessage());
    }

    public static ResponseEntity<EntityException> createResponseEntity(HttpStatus status, String message) {
        EntityException entityException = new EntityException();
        entityException.setStatus(status.value());
        entityException.setMessage(message);
        entityException.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(entityException, status);
    }
}
