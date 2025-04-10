package com.pstag.com.service;

import com.pstag.com.model.Car;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

public class CarsSortService {

    public void sort(List<Car> cars, String sortOption) {
        if (cars == null || cars.isEmpty()) {
            System.out.println("[WARN] Car list is null or empty. Sorting cannot be performed.");
            return;
        }
        if (sortOption == null || sortOption.isEmpty()) {
            System.out.println("[WARN] Sort option is null or empty.");
            return;
        }


        switch (sortOption.toLowerCase()) {
            case "price_asc":
                cars.sort(Comparator.comparingDouble(car -> car.getDisplay().getPrice()));
                break;

            case "price_desc":
                cars.sort(Comparator.comparingDouble((Car car) ->
                        car != null && car.getDisplay() != null
                                ? car.getDisplay().getPrice()
                                : Double.MIN_VALUE
                ).reversed());

                break;

            case "year_asc":
                cars.sort(Comparator.comparing(Car::getReleaseDate, Comparator.nullsLast(LocalDate::compareTo)));
                break;

            case "year_desc":
                cars.sort(Comparator.comparing(Car::getReleaseDate,
                        Comparator.nullsLast(LocalDate::compareTo)).reversed());
                break;

            default:
                System.out.println("[WARN] Invalid option: " + sortOption);
        }
    }
}
