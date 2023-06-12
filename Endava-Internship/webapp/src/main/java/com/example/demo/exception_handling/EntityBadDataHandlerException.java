package com.example.demo.exception_handling;

import com.example.demo.custom_exception.EntityException;
import com.example.demo.custom_exception.NoUniqueDataException;
import com.example.demo.custom_exception.NotFoundedDataException;
import com.example.demo.custom_exception.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.example.demo.exception_handling.GlobalHandlerException.createResponseEntity;

@ControllerAdvice
public class EntityBadDataHandlerException {
    @ExceptionHandler
    public ResponseEntity<EntityException> noUniqueEmailOrPhoneNumber(NoUniqueDataException exception) {

        return createResponseEntity(HttpStatus.NOT_ACCEPTABLE, exception.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<EntityException> invalidInputData(ValidationException exception) {

        return createResponseEntity(HttpStatus.NOT_ACCEPTABLE, exception.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<EntityException> notExistedEntity(NotFoundedDataException exception) {

        return createResponseEntity(HttpStatus.NOT_FOUND, exception.getMessage());
    }
}
