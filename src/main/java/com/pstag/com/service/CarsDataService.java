package com.pstag.com.service;

import com.pstag.com.model.Car;
import com.pstag.com.model.CarBrand;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarsDataService {
    private static final Map<String, String> modelToBrand = new HashMap<>();

    static {
        modelToBrand.put("RAV4", "Toyota");
        modelToBrand.put("Civic", "Honda");
        modelToBrand.put("F-150", "Ford");
        modelToBrand.put("Model X", "Tesla");
        modelToBrand.put("330i", "BMW");
        modelToBrand.put("Q5", "Audi");
        modelToBrand.put("Silverado", "Chevrolet");
        modelToBrand.put("C-Class", "Mercedes-Benz");
        modelToBrand.put("Rogue", "Nissan");
        modelToBrand.put("Elantra", "Hyundai");
    }

    public void mergeData(List<Car> cars, List<CarBrand> brands) {
        Map<String, LocalDate> brandDateMap = new HashMap<>();

        for (CarBrand info : brands) {
            brandDateMap.put(info.getBrand(), info.getReleaseDate());
        }

        for (Car car : cars) {
            String model = car.getModel();
            String brand = modelToBrand.getOrDefault(model, "Unknown");
            car.setBrand(brand);

            LocalDate releaseDate = brandDateMap.get(brand);
            car.setReleaseDate(releaseDate);
        }
    }

    public void displayPriceByType(List<Car> cars) {
        Map<String, String> currencyByType = Map.of(
                "suv", "EUR",
                "sedan", "JPY",
                "truck", "USD"
        );

        for (Car car : cars) {
            String type = car.getType() != null ? car.getType().toLowerCase() : "";
            String preferredCurrency = currencyByType.getOrDefault(type, "USD");

            if (car.getPrices() == null) continue;

            car.getPrices().stream()
                    .filter(p -> preferredCurrency.equalsIgnoreCase(p.getCurrency()))
                    .findFirst()
                    .ifPresent(p -> {
                        car.getDisplay().setCurrency(p.getCurrency());
                        car.getDisplay().setPrice(p.getPrice());
                    });
        }
    }
}
