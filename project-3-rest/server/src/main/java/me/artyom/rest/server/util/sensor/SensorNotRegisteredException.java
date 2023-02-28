package me.artyom.rest.server.util.sensor;

public class SensorNotRegisteredException extends RuntimeException {

    public SensorNotRegisteredException(String message) {
        super(message);
    }
}
