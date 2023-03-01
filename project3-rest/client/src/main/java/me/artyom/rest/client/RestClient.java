package me.artyom.rest.client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RestClient {

    // 'http://' is mandatory
    private static final String SENSORS_REGISTRATION = "http://localhost:8080/sensors/registration";
    private static final String MEASUREMENTS_ADD = "http://localhost:8080/measurements/add";

    static final String SENSOR = "sensor";

    private static final RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) {
        // Sensor
        registerSensor(sensorJsonData(SENSOR));

        // Measurements
        Random random = new Random();

        int maxValue = 35;

        for (int i = 0; i < 1000; i++) {
            float value = random.nextFloat() * maxValue;  // (0.0; 1.0) * maxValue
            if (random.nextBoolean()) value = value * -1;  // negative temperature with the same maxValue

            addMeasurement(measurementJsonData(value, random.nextBoolean(), SENSOR));
        }
    }

    private static void registerSensor(Map<String, String> body) {
        postJsonData(SENSORS_REGISTRATION, body);
    }

    private static void addMeasurement(Map<String, Object> body) {
        postJsonData(MEASUREMENTS_ADD, body);
    }

    private static void postJsonData(String url, Map<String, ?> body) {  // Wildcard extends Object by default
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, ?>> request = new HttpEntity<>(body, headers);

        restTemplate.postForObject(url, request, String.class);
    }

    private static Map<String, Object> measurementJsonData(float value, boolean raining, String sensor) {
        Map<String, Object> jsonData = new HashMap<>();
        jsonData.put("value", value);
        jsonData.put("raining", raining);
        jsonData.put("sensor", sensorJsonData(sensor));

        return jsonData;
    }

    private static Map<String, String> sensorJsonData(String name) {
        return Map.of("name", name);
    }
}
