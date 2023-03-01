package me.artyom.rest.client;

import me.artyom.rest.client.dto.MeasurementDTO;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TemperatureChart {

    private static final String MEASUREMENTS = "http://localhost:8080/measurements";

    private static final RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) {
        MeasurementDTO[] measurements = getMeasurements();

        if (measurements.length == 0) {
            System.err.println("Couldn't get any measurements");
            return;
        }

        Arrays.stream(measurements).forEach(System.out::println);

        List<Double> yData = Arrays.stream(measurements).map(MeasurementDTO::getValue).collect(Collectors.toList());

        displayChart(yData);
    }

    private static MeasurementDTO[] getMeasurements() {
        return restTemplate.getForObject(MEASUREMENTS, MeasurementDTO[].class);
    }

    private static void displayChart(List<Double> yData) {
        List<Integer> xData = IntStream.range(0, yData.size()).boxed().collect(Collectors.toList());
        // Create Chart
        XYChart chart = QuickChart.getChart("Sample Chart", "X", "Y", "y(x)", xData, yData);
        // Show it
        new SwingWrapper(chart).displayChart();
    }
}
