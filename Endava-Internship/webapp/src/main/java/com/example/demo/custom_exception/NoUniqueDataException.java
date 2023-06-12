package com.example.demo.custom_exception;

public class NoUniqueDataException extends RuntimeException{
    public NoUniqueDataException() {
    }

    public NoUniqueDataException(String message) {
        super(message);
    }

    public NoUniqueDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoUniqueDataException(Throwable cause) {
        super(cause);
    }

    public NoUniqueDataException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
