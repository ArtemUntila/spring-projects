package me.artyom.rest.server.util;

import me.artyom.rest.server.model.Sensor;
import me.artyom.rest.server.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class SensorValidator implements Validator {

    private final SensorService sensorService;

    @Autowired
    public SensorValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(Sensor.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Sensor sensor = (Sensor) target;

        Optional<Sensor> optionalSensor = sensorService.findByName(sensor.getName());

        if (optionalSensor.isPresent())
            errors.rejectValue("name", "", "Sensor with that name already exists");
    }
}
