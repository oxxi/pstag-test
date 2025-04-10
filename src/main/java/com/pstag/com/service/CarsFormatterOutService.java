package com.pstag.com.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.pstag.com.model.Car;
import com.pstag.com.model.Cars;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;

import java.util.List;

public class CarsFormatterOutService {

    public CarsFormatterOutService() {
    }

    public void print(List<Car> cars, String printOption){
        if (printOption == null) printOption = "table";
        switch (printOption.toLowerCase()) {
            case "table":
                table(cars);
                break;

            case "json":
                json(cars);
                break;

            case "xml":
                xml(cars);
                break;

            default:
                System.out.println("[WARN] invalid option: " + printOption);
                table(cars);
        }
    }


    private void xml(List<Car> cars) {
        try {
            Cars wrapper = new Cars(cars);

            JAXBContext context = JAXBContext.newInstance(Cars.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            marshaller.marshal(wrapper, System.out);

        } catch (Exception e) {
            System.err.println("Error printing XML output: " + e.getMessage());
        }
    }

    private void json(List<Car> cars){
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            mapper.enable(SerializationFeature.INDENT_OUTPUT);

            String jsonOutput = mapper.writeValueAsString(cars);
            System.out.println(jsonOutput);

        } catch (Exception e) {
            System.err.println("Error printing JSON output: " + e.getMessage());
        }
    }

    private void table(List<Car> cars) {
        if (cars == null || cars.isEmpty()) {
            System.out.println("No cars to display.");
            return;
        }

        // headers
        String[] headers = { "Model", "Type", "Brand", "Price", "Currency", "Release Date" };

        // Max column
        int modelWidth = getMaxWidth(cars, Car::getModel, headers[0]);
        int typeWidth = getMaxWidth(cars, Car::getType, headers[1]);
        int brandWidth = getMaxWidth(cars, Car::getBrand, headers[2]);
        int priceWidth = getMaxWidth(cars, c -> c.getDisplay().getPrice().toString(), headers[3]);
        int currencyWidth = getMaxWidth(cars, c->c.getDisplay().getCurrency(), headers[4]);
        int dateWidth = getMaxWidth(cars, c -> {
            return (c.getReleaseDate() != null) ? c.getReleaseDate().toString() : "N/A";
        }, headers[5]);

        String format = String.format("| %%-%ds | %%-%ds | %%-%ds | %%-%ds | %%-%ds | %%-%ds |\n",
                modelWidth, typeWidth, brandWidth, priceWidth, currencyWidth, dateWidth);

        String line = "+" + "-".repeat(modelWidth + 2) +
                "+" + "-".repeat(typeWidth + 2) +
                "+" + "-".repeat(brandWidth + 2) +
                "+" + "-".repeat(priceWidth + 2) +
                "+" + "-".repeat(currencyWidth + 2) +
                "+" + "-".repeat(dateWidth + 2) + "+";

        // print headers
        System.out.println(line);
        System.out.printf(format,headers);
        System.out.println(line);

        // print rows
        for (Car car : cars) {
            System.out.printf(format,
                    car.getModel(),
                    car.getType(),
                    car.getBrand(),
                    car.getDisplay().getPrice(),
                    car.getDisplay().getCurrency(),
                    car.getReleaseDate() != null ? car.getReleaseDate().toString() : "N/A"
            );
        }

        System.out.println(line);
    }

    private int getMaxWidth(List<Car> cars, java.util.function.Function<Car, String> extractor, String header) {
        int max = header.length();
        for (Car car : cars) {
            String value = extractor.apply(car);
            if (value != null && value.length() > max) {
                max = value.length();
            }
        }
        return max;
    }
}
