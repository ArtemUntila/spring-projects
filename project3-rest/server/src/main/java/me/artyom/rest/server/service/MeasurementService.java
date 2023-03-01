package me.artyom.rest.server.service;

import me.artyom.rest.server.model.Measurement;
import me.artyom.rest.server.repository.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MeasurementService {

    private final MeasurementRepository measurementRepository;
    private final SensorService sensorService;

    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository, SensorService sensorService) {
        this.measurementRepository = measurementRepository;
        this.sensorService = sensorService;
    }

    public List<Measurement> findAll() {
        return measurementRepository.findAll();
    }

    public List<Measurement> findByRainingTrue() {
        return measurementRepository.findByRainingTrue();
    }

    @Transactional
    public void save(Measurement measurement) {
        measurement.setCreatedAt(LocalDateTime.now());
        // Get Sensor from Persistence Context
        measurement.setSensor(sensorService.findByName(measurement.getSensor().getName()).get());

        measurementRepository.save(measurement);
    }
}
