package me.artyom.rest.server.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class MeasurementDTO {

    @NotNull
    @Min(-100)
    @Max(100)
    private Float value;

    @NotNull
    private Boolean raining;

    // Despite it is DTO, property name must be equal to corresponding name in Model
    @NotNull
    private SensorDTO sensor;

    public MeasurementDTO() {
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public Boolean isRaining() {
        return raining;
    }

    public void setRaining(Boolean raining) {
        this.raining = raining;
    }

    public SensorDTO getSensor() {
        return sensor;
    }

    public void setSensor(SensorDTO sensor) {
        this.sensor = sensor;
    }
}
