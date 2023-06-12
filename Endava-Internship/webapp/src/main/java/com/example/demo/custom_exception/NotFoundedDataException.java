package com.example.demo.custom_exception;

public class NotFoundedDataException extends RuntimeException{
    public NotFoundedDataException() {
    }

    public NotFoundedDataException(String message) {
        super(message);
    }

    public NotFoundedDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundedDataException(Throwable cause) {
        super(cause);
    }

    public NotFoundedDataException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}