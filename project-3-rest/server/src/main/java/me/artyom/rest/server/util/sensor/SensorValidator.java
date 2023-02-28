package me.artyom.rest.server.util.sensor;

import me.artyom.rest.server.model.Sensor;
import me.artyom.rest.server.service.SensorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class SensorValidator implements Validator {

    private final SensorsService sensorsService;

    @Autowired
    public SensorValidator(SensorsService sensorsService) {
        this.sensorsService = sensorsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(Sensor.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Sensor sensor = (Sensor) target;

        Optional<Sensor> optionalSensor = sensorsService.findByName(sensor.getName());

        if (optionalSensor.isPresent())
            errors.rejectValue("name", "", "Sensor with that name already exists");
    }
}
