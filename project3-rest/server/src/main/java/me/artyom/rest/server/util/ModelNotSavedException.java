package me.artyom.rest.server.util;

public class ModelNotSavedException extends RuntimeException {

    public ModelNotSavedException(String message) {
        super(message);
    }
}
