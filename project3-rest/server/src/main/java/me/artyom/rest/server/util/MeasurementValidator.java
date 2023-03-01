package me.artyom.rest.server.util;

import me.artyom.rest.server.model.Measurement;
import me.artyom.rest.server.model.Sensor;
import me.artyom.rest.server.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class MeasurementValidator implements Validator {

    private final SensorService sensorService;

    @Autowired
    public MeasurementValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(Measurement.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Measurement measurement = (Measurement) target;

        Sensor sensor = measurement.getSensor();

        if (sensor == null) return;

        if (sensorService.findByName(sensor.getName()).isEmpty())
            errors.rejectValue("sensor", "", "Sensor with given name doesn't exist");
    }
}
