package com.pstag.com.service;

import com.pstag.com.model.Car;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CarFilterService {

    public CarFilterService() {
    }

    public List<Car> applyFilters(List<Car> cars, String filterString) {
        if (filterString == null || filterString.isEmpty()) return cars;

        List<Car> filtered = new ArrayList<>(cars);

        String[] filters = filterString.split(",");

        for (String filter : filters) {
            filter = filter.trim();

            if (filter.startsWith("brand:")) {
                String value = filter.substring("brand:".length()).trim();
                filtered.removeIf(car -> !car.getBrand().equalsIgnoreCase(value));

            } else if (filter.startsWith("price<")) {
                double value = Double.parseDouble(filter.substring("price<".length()).trim());
                filtered.removeIf(car -> car.getDisplay().getPrice() >= value);

            } else if (filter.startsWith("price>")) {
                double value = Double.parseDouble(filter.substring("price>".length()).trim());
                filtered.removeIf(car -> car.getDisplay().getPrice() <= value);

            } else if (filter.startsWith("date<")) {
                LocalDate value = LocalDate.parse(filter.substring("date<".length()).trim());
                filtered.removeIf(car -> car.getReleaseDate() == null || !car.getReleaseDate().isBefore(value));

            } else if (filter.startsWith("date>")) {
                LocalDate value = LocalDate.parse(filter.substring("date>".length()).trim());
                filtered.removeIf(car -> car.getReleaseDate() == null || !car.getReleaseDate().isAfter(value));
            }
        }

        return filtered;
    }
}
