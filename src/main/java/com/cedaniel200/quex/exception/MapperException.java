package com.cedaniel200.quex.exception;

public class MapperException extends Exception {

    public MapperException(String message) {
        super(message);
    }

    public MapperException(String message, Throwable cause) {
        super(message, cause);
    }
}
